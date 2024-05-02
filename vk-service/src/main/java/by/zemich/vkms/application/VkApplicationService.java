package by.zemich.vkms.application;

import by.zemich.vkms.application.commandservices.VkPostCommandService;
import by.zemich.vkms.application.outboundservices.alc.ExternalSupplierFeignClient;
import by.zemich.vkms.application.outboundservices.alc.ExternalVKService;
import by.zemich.vkms.application.outboundservices.alc.model.VKPostQuery;
import by.zemich.vkms.application.queryservices.VkPostQueryService;
import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import by.zemich.vkms.domain.model.entities.UUid;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoSizes;
import com.vk.api.sdk.objects.wall.WallpostAttachment;
import com.vk.api.sdk.objects.wall.WallpostFull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;

@Service
@Slf4j
public class VkApplicationService {

    private final VkPostCommandService vkPostCommandService;
    private final ExternalVKService externalVKService;
    private final VkPostQueryService vkPostQueryService;
    private final ExternalSupplierFeignClient externalSupplierFeignClient;
    //TODO подтянуть из config server'а
    private Integer actualPeriodOfDay = 7;

    private final Predicate<WallpostFull> outOfTimePredicate = wallpostFull -> {
        LocalDateTime published = LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow"));
        if (LocalDate.now().until(published.toLocalDate()).getDays() > actualPeriodOfDay) {
            return true;
        }
        return false;
    };

    public VkApplicationService(VkPostCommandService vkPostCommandService, ExternalVKService externalVKService, VkPostQueryService vkPostQueryService, ExternalSupplierFeignClient externalSupplierFeignClient) {
        this.vkPostCommandService = vkPostCommandService;
        this.externalVKService = externalVKService;
        this.vkPostQueryService = vkPostQueryService;
        this.externalSupplierFeignClient = externalSupplierFeignClient;
    }


    @Scheduled(cron ="* * * * 30 *")
    public void checkNewPosts() {

        externalSupplierFeignClient.getAll().forEach(
                supplier -> {
                    final VKPostQuery query = creteQuery(supplier.getVkId());

                    externalVKService.doPostRequest(query).getItems().stream()
                            .filter(outOfTimePredicate)
                            .filter(wallpostFull -> Objects.nonNull(wallpostFull.getAttachments()))
                            .filter(wallpostFull -> Objects.nonNull(wallpostFull.getText()))
                            .filter(wallpostFull -> vkPostQueryService.existsById(wallpostFull.getId()))
                            .map(wallpostFull -> this.buildCreateVkPostCommand(wallpostFull, UUid.of(supplier.getUuid())))
                            .forEach(vkPostCommandService::createPost);
                }
        );
    }

    private VKPostQuery creteQuery(String supplierVkId) {
        return new VKPostQuery()
                .setCount(5)
                .setInterval(15)
                .setOffset(0)
                .setSupplierVkId(supplierVkId);
    }

    private CreateVkPostCommand buildCreateVkPostCommand(WallpostFull wallpostFull, UUid supplierUuid){
        List<URI> uriList = wallpostFull.getAttachments().stream()
                .map(WallpostAttachment::getPhoto)
                .map(Photo::getSizes)
                .flatMap(photoSizes -> photoSizes.stream())
                .max(Comparator.comparingInt(PhotoSizes::getWidth))
                .map(PhotoSizes::getUrl)
                .stream().toList();


        return new CreateVkPostCommand().setSupplierUuid(supplierUuid)
                .setPostId(wallpostFull.getPostId())
                .setOwnerId(wallpostFull.getOwnerId())
                .setPostText(wallpostFull.getText())
                .setImagesLinkList(uriList);
    }

}

