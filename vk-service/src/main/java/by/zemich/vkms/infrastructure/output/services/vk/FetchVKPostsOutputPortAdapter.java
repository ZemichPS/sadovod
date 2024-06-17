package by.zemich.vkms.infrastructure.output.services.vk;

import by.zemich.vkms.application.internal.ports.output.FetchVkPostsOutputPort;
import by.zemich.vkms.application.internal.usecases.VkPostManagementUseCase;
import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.entities.Supplier;
import by.zemich.vkms.domain.model.exceptions.GenericSpecificationException;
import by.zemich.vkms.domain.model.valueobjects.*;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoSizes;
import com.vk.api.sdk.objects.wall.GetFilter;
import com.vk.api.sdk.objects.wall.WallpostAttachment;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.*;
import java.util.function.Predicate;

@Component
public class FetchVKPostsOutputPortAdapter implements FetchVkPostsOutputPort {

    private static final Logger log = LoggerFactory.getLogger(FetchVKPostsOutputPortAdapter.class);
    private final VkApiClient vkApiClient;
    private final UserActor userActor;
    //TODO подтянуть из config server'а
    private Integer actualPeriodOfDay = 7;

    private final Predicate<WallpostFull> outOfTimePredicate = wallpostFull -> {
        LocalDateTime published = LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow"));
        return Period.between(LocalDate.now(), published.toLocalDate()).getDays() <= actualPeriodOfDay;
    };

    private final Predicate<WallpostFull> containsText = wallpostFull -> Objects.nonNull(wallpostFull.getText());
    private final Predicate<WallpostFull> containsAttachments = wallpostFull -> Objects.nonNull(wallpostFull.getAttachments());
    private final Predicate<WallpostFull> containsId = wallpostFull -> Objects.nonNull(wallpostFull.getId());
    private final Predicate<WallpostFull> containsDate = wallpostFull -> Objects.nonNull(wallpostFull.getDate());

    public FetchVKPostsOutputPortAdapter(VkApiClient vkApiClient, UserActor userActor) {
        this.vkApiClient = vkApiClient;
        this.userActor = userActor;
    }

    @Override
    public List<VkPost> fetchPost(VkPostManagementUseCase.FetchVKPostQuery query) {

        GetResponse getResponse = doPostRequest(query);
        return getResponse.getItems().stream()
                .filter(outOfTimePredicate
                        .and(containsText)
                        .and(containsAttachments)
                        .and(containsId)
                        .and(containsDate)
                )
                .map(wallpostFull -> {

                    List<by.zemich.vkms.domain.model.entities.Photo> photoList = wallpostFull.getAttachments().stream()
                            .map(WallpostAttachment::getPhoto)
                            .filter(Objects::nonNull)
                            .map(Photo::getSizes)
                            .map(lists -> lists.stream().filter(Objects::nonNull).max(Comparator.comparingInt(PhotoSizes::getWidth)).stream().toList())
                            .flatMap(Collection::stream)
                            .map(PhotoSizes::getUrl)
                            .map(uri -> new by.zemich.vkms.domain.model.entities.Photo(UUID.randomUUID(), uri.getPath()))
                            .toList();

                    DateInfo dateInfo = new DateInfo(LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow")));

                    Supplier supplier = new Supplier();
                    supplier.setUuid(query.getSupplierUUID());
                    supplier.setVkId(query.getSupplierVkId());
                    supplier.setName(query.getSupplierName());

                    String text = wallpostFull.getText();
                    Text postText = new Text(text);
                    VkPost vkPost = new VkPost();
                    try {
                        vkPost.setId(new Id(UUID.randomUUID()));
                        vkPost.setDateInfo(dateInfo);
                        //TODO заменить
                        vkPost.setVkPostId(new VkPostId(wallpostFull.getId(), wallpostFull.getHash()));
                        vkPost.setText(new Text(text));
                        vkPost.setPhotos(new Photos(new ArrayList<>()));
                        vkPost.addText(postText);
                        vkPost.addSupplier(supplier);
                        photoList.forEach(vkPost::addPhoto);
                        return vkPost;
                    } catch (GenericSpecificationException e) {
                        log.error(e.getMessage());
                        return null;
                    }

                })
                .filter(Objects::nonNull)
                .toList();
    }

    public GetResponse doPostRequest(VkPostManagementUseCase.FetchVKPostQuery query) {
        try {
            return getVkPostResponse(query);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    private GetResponse getVkPostResponse(VkPostManagementUseCase.FetchVKPostQuery query) throws ClientException, ApiException {
        return vkApiClient.wall().get(userActor)
                .domain(query.getSupplierVkId())
                .count(query.getCount())
                .offset(query.getOffset())
                .filter(GetFilter.valueOf("OWNER"))
                .execute();
    }

}
