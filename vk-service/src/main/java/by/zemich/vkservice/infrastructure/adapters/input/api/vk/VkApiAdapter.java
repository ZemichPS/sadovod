package by.zemich.vkservice.infrastructure.adapters.input.api.vk;

import by.zemich.vkservice.application.model.VKPostQuery;
import by.zemich.vkservice.domain.model.vkpost.VkPost;
import by.zemich.vkservice.application.ports.input.VKServicePort;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.PhotoSizes;
import com.vk.api.sdk.objects.wall.GetFilter;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
public class VkApiAdapter implements VKServicePort {
    private final VkApiClient vkApiClient;
    private final com.vk.api.sdk.client.actors.UserActor UserActor;

    public VkApiAdapter(VkApiClient vkApiClient,
                        com.vk.api.sdk.client.actors.UserActor userActor) {
        this.vkApiClient = vkApiClient;
        UserActor = userActor;
    }

    @Override
    public List<VkPost> getPosts(VKPostQuery query) {

        try {
            return requestPosts(query);

        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }

    }


    private List<VkPost> requestPosts(VKPostQuery query) throws ClientException, ApiException {

       GetResponse getResponse = vkApiClient.wall().get(UserActor)
                .domain(query.getSupplier().getVkId())
                .count(query.getCount())
                .offset(query.getOffset())
                .filter(GetFilter.valueOf("OWNER"))
                .execute();

        return getResponse.getItems().stream()
                .map(wallpostFull -> this.convertWallpostFullToVkPostDto(wallpostFull, query))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(vkPostDto -> CollectionUtils.isNotEmpty(vkPostDto.getImagesLinkList()))
                .toList();
    }


    private URI buildLinkToPost(String supplierId, WallpostFull wallpostFull) {
        try {
            return new URIBuilder().setScheme("https")
                    .setHost("vk.com")
                    .setPath("/" + supplierId)
                    .addParameter("w", "wall" + wallpostFull.getOwnerId() + "_" + wallpostFull.getId())
                    .build();
        } catch (URISyntaxException e) {
            try {
                log.error("Failed to make link on post", e);
                throw new URISyntaxException(e.getInput(), e.getReason());

            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private Optional<VkPost> convertWallpostFullToVkPostDto(WallpostFull wallpostFull, VKPostQuery query) {

        if(wallpostFull.getDate() == null) return Optional.empty();

        LocalDateTime published = LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow"));
        LocalDate toDay = LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()), TimeZone.getDefault().toZoneId()).toLocalDate();


        if (toDay.until(published.toLocalDate()).getDays()  > query.getInterval()) {
            return Optional.empty();
        }

        if (wallpostFull.getAttachments().isEmpty()) return Optional.empty();

        VkPost vkPost = VkPost.builder()
                .id(wallpostFull.getId())
                .text(wallpostFull.getText())
                .publishedAt(published)
                .link(buildLinkToPost(query.getSupplier().getVkId(), wallpostFull).toString())
                .imagesLinkList(new ArrayList<>())
                .build();


        wallpostFull.getAttachments().forEach(wallpostAttachment -> {
            if (wallpostAttachment.getPhoto() == null) return;
            if (wallpostAttachment.getPhoto().getSizes().isEmpty()) return;

            wallpostAttachment.getPhoto().getSizes().stream()
                    .max(Comparator.comparingInt(PhotoSizes::getWidth))
                    .map(PhotoSizes::getUrl)
                    .map(URI::toString)
                    .ifPresent(vkPost.getImagesLinkList()::add);
        });

        return Optional.of(vkPost);

    }



}
