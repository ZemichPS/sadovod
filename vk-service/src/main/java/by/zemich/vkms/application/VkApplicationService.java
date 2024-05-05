package by.zemich.vkms.application;

import by.zemich.vkms.application.internal.commandservices.VkPostCommandService;
import by.zemich.vkms.application.internal.outboundservices.alc.ExternalSupplierFeignClient;
import by.zemich.vkms.application.internal.outboundservices.alc.ExternalVKService;
import by.zemich.vkms.application.internal.outboundservices.alc.model.VKPostQuery;
import by.zemich.vkms.application.internal.queryservices.VkPostQueryService;
import by.zemich.vkms.domain.model.aggregates.VkPostIdBKey;
import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoSizes;
import com.vk.api.sdk.objects.wall.WallpostAttachment;
import com.vk.api.sdk.objects.wall.WallpostFull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.*;
import java.util.*;
import java.util.function.Predicate;

@Service
@Slf4j
//@EnableScheduling
public class VkApplicationService {

    private final VkPostCommandService vkPostCommandService;
    private final ExternalVKService externalVKService;
    private final VkPostQueryService vkPostQueryService;
    private final ExternalSupplierFeignClient externalSupplierFeignClient;
    //TODO подтянуть из config server'а
    private Integer actualPeriodOfDay = 7;

    private final Predicate<WallpostFull> outOfTimePredicate = wallpostFull -> {
        LocalDateTime published = LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow"));
        return Period.between(LocalDate.now(), published.toLocalDate()).getDays() <= actualPeriodOfDay;
    };

    public VkApplicationService(VkPostCommandService vkPostCommandService, ExternalVKService externalVKService, VkPostQueryService vkPostQueryService, ExternalSupplierFeignClient externalSupplierFeignClient) {
        this.vkPostCommandService = vkPostCommandService;
        this.externalVKService = externalVKService;
        this.vkPostQueryService = vkPostQueryService;
        this.externalSupplierFeignClient = externalSupplierFeignClient;
    }


    @Scheduled(cron = "*/15 * * * * *")
    public void checkNewPosts() {
        System.out.println("start debuging");
        externalSupplierFeignClient.getAll().forEach(
                supplier -> {
                    VKPostQuery query = creteQuery(supplier.getVkId());

                    externalVKService.doPostRequest(query).getItems().stream()
                            .filter(outOfTimePredicate)
                            .filter(wallpostFull -> Objects.nonNull(wallpostFull.getAttachments()))
                            .filter(wallpostFull -> Objects.nonNull(wallpostFull.getText()))
                            .filter(wallpostFull -> Objects.nonNull(wallpostFull.getId()))
                            .filter(wallpostFull -> Objects.nonNull(wallpostFull.getDate()))
                            .filter(wallpostFull -> !vkPostQueryService.existsByBKey(new VkPostIdBKey(wallpostFull.getId(), wallpostFull.getOwnerId())))
                            .map(wallpostFull -> this.buildCreateVkPostCommand(wallpostFull, supplier.getUuid()))
                            .forEach(vkPostCommandService::createPost);
                }
        );
    }

    private VKPostQuery creteQuery(String supplierVkId) {
        return new VKPostQuery()
                .setCount(30)
                .setInterval(15)
                .setOffset(0)
                .setSupplierVkId(supplierVkId);
    }

    private CreateVkPostCommand buildCreateVkPostCommand(WallpostFull wallpostFull, java.util.UUID supplierUuid) {

        final List<URI> uriList = wallpostFull.getAttachments().stream()
                .map(WallpostAttachment::getPhoto)
                .filter(Objects::nonNull)
                .map(Photo::getSizes)
                .map(lists -> lists.stream().filter(Objects::nonNull).max(Comparator.comparingInt(PhotoSizes::getWidth)).stream().toList())
                .flatMap(photoSizes -> photoSizes.stream())
                .map(PhotoSizes::getUrl)
                .toList();


        return new CreateVkPostCommand().setSupplierUuid(supplierUuid)
                .setPostId(wallpostFull.getId())
                .setOwnerId(wallpostFull.getOwnerId())
                .setPostText(wallpostFull.getText())
                .setPublishedAt(LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow")))
                .setImagesLinkList(uriList);
    }

}

