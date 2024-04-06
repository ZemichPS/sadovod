package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.model.VKPostQuery;
import by.zemich.parserservice.core.model.VkPostDto;
import by.zemich.parserservice.core.enums.EProductType;
import by.zemich.parserservice.service.api.VKService;
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
import java.time.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class VKServiceImpl implements VKService {
    private final VkApiClient vkApiClient;
    private final com.vk.api.sdk.client.actors.UserActor UserActor;

    public VKServiceImpl(VkApiClient vkApiClient,
                         com.vk.api.sdk.client.actors.UserActor userActor) {
        this.vkApiClient = vkApiClient;
        UserActor = userActor;
    }

    @Override
    public List<VkPostDto> getPosts(VKPostQuery query) {

        try {
            return requestPosts(query);

        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }

    }


    private List<VkPostDto> requestPosts(VKPostQuery query) throws ClientException, ApiException {

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

    private Optional<VkPostDto> convertWallpostFullToVkPostDto(WallpostFull wallpostFull, VKPostQuery query) {

        if(wallpostFull.getDate() == null) return Optional.empty();

        LocalDateTime published = LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow"));
        LocalDate toDay = LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()), TimeZone.getDefault().toZoneId()).toLocalDate();


        if (toDay.until(published.toLocalDate()).getDays()  > query.getInterval()) {
            return Optional.empty();
        }

        if (wallpostFull.getAttachments().isEmpty()) return Optional.empty();

        VkPostDto vkPostDto = VkPostDto.builder()
                .id(wallpostFull.getId())
                .supplierDto(query.getSupplier())
                .productType(EProductType.CLOTHES)
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
                    .ifPresent(vkPostDto.getImagesLinkList()::add);
        });

        return Optional.of(vkPostDto);

    }



}
