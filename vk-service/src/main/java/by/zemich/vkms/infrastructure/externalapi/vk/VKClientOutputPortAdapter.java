package by.zemich.vkms.infrastructure.externalapi.vk;

import by.zemich.vkms.application.internal.ports.output.VkClientOutputPort;
import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import by.zemich.vkms.domain.model.queries.FetchVKPostQuery;
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
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class VKClientOutputPortAdapter implements VkClientOutputPort {

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

    public VKClientOutputPortAdapter(VkApiClient vkApiClient, UserActor userActor) {
        this.vkApiClient = vkApiClient;
        this.userActor = userActor;
    }

    @Override
    public List<CreateVkPostCommand> fetchPost(FetchVKPostQuery query) {
        GetResponse getResponse = doPostRequest(query);
        return getResponse.getItems().stream()
                .filter(outOfTimePredicate
                        .and(containsText)
                        .and(containsAttachments)
                        .and(containsId)
                        .and(containsDate)
                )
                .map(wallpostFull -> buildCreateVkPostCommand(wallpostFull, query.getSupplierUUID()))
                .toList();
    }



    public GetResponse doPostRequest(FetchVKPostQuery query) {
        try {
            return getVkPostResponse(query);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    private GetResponse getVkPostResponse(FetchVKPostQuery query) throws ClientException, ApiException {

        return vkApiClient.wall().get(userActor)
                .domain(query.getSupplierVkId())
                .count(query.getCount())
                .offset(query.getOffset())
                .filter(GetFilter.valueOf("OWNER"))
                .execute();
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


        return CreateVkPostCommand.builder().supplierUuid(supplierUuid)
                .postId(wallpostFull.getId())
                .ownerId(wallpostFull.getOwnerId())
                .postText(wallpostFull.getText())
                .publishedAt(LocalDateTime.ofInstant(Instant.ofEpochSecond(wallpostFull.getDate()), ZoneId.of("Europe/Moscow")))
                .imagesLinkList(uriList).build();
    }

}
