package by.zemich.vkms.application;

import by.zemich.vkms.application.commandservices.VkPostCommandService;
import by.zemich.vkms.application.queryservices.VkPostQueryService;
import by.zemich.vkms.application.outboundservices.alc.model.VKPostQuery;
import by.zemich.vkms.domain.model.aggregates.VkPostId;
import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import by.zemich.vkms.domain.model.entities.Supplier;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoSizes;
import com.vk.api.sdk.objects.wall.GetFilter;
import com.vk.api.sdk.objects.wall.WallpostAttachment;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;

@Service
@Slf4j
public class VkApplicationService {
    private final VkApiClient vkApiClient;
    private final com.vk.api.sdk.client.actors.UserActor userActor;
    private final VkPostCommandService vkPostCommandService;
    private final VkPostQueryService vkPostQueryService;
    private final Integer actualPeriodOfDay = 7;


    private final Predicate<WallpostFull> outOfTimePredicate = wallpostFull -> {
        LocalDateTime published = LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow"));
        if (LocalDate.now().until(published.toLocalDate()).getDays() > actualPeriodOfDay) {
            return true;
        }
        return false;
    };


    public VkApplicationService(VkApiClient vkApiClient,
                                com.vk.api.sdk.client.actors.UserActor userActor,
                                VkPostCommandService vkPostCommandService,
                                VkPostQueryService vkPostQueryService) {
        this.vkApiClient = vkApiClient;
        this.userActor = userActor;
        this.vkPostCommandService = vkPostCommandService;
        this.vkPostQueryService = vkPostQueryService;
    }

    @Scheduled(cron = "* 10 * * * *", initialDelay = 10_000)
    public void checkNewPosts() {
        Supplier currentSupplier = null;

        final List<VkPostId> vkPostIds = vkPostQueryService.getSuppliers().stream()
                .peek(supplier -> currentSupplier = supplier)
                .map(this::creteQuery)
                .map(this::getVkPostResponse)
                .flatMap(response -> response.getItems().stream())
                .filter(outOfTimePredicate)
                .filter(wallpostFull -> Objects.nonNull(wallpostFull.getAttachments()))
                .filter(wallpostFull -> Objects.nonNull(wallpostFull.getText()))
                .filter(wallpostFull -> vkPostQueryService.existsById(wallpostFull.getId()))
                .map(wallpostFull -> {

                    List<URI> uriList = wallpostFull.getAttachments().stream()
                            .map(WallpostAttachment::getPhoto)
                            .map(Photo::getSizes)
                            .flatMap(photoSizes -> photoSizes.stream())
                            .max(Comparator.comparingInt(PhotoSizes::getWidth))
                            .map(PhotoSizes::getUrl)
                            .stream().toList();

                    CreateVkPostCommand createVkPostCommand = new CreateVkPostCommand().setSupplier(currentSupplier)
                            .setPostId(wallpostFull.getPostId())
                            .setOwnerId(wallpostFull.getOwnerId())
                            .setPostText(wallpostFull.getText())
                            .setPostLink(this.buildLinkToPost(currentSupplier.getVkId(), wallpostFull))
                            .setImagesLinkList(uriList);

                    return new CreateVkPostCommand();
                })
                .map(vkPostCommandService::createPost)
                .toList();

    }

    private VKPostQuery creteQuery(Supplier supplier) {
        return new VKPostQuery()
                .setCount(1)
                .setInterval(15)
                .setOffset(0)
                .setSupplier(supplier);
    }
    private GetResponse getVkPostResponse(VKPostQuery query) {

        try {
            return vkApiClient.wall().get(userActor)
                    .domain(query.getSupplier().getVkId())
                    .count(query.getCount())
                    .offset(query.getOffset())
                    .filter(GetFilter.valueOf("OWNER"))
                    .execute();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }


    private URL buildLinkToPost(String supplierId, WallpostFull wallpostFull) {

        try {
            return new URIBuilder().setScheme("https")
                    .setHost("vk.com")
                    .setPath("/" + supplierId)
                    .addParameter("w", "wall" + wallpostFull.getOwnerId() + "_" + wallpostFull.getId())
                    .build().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }



}

