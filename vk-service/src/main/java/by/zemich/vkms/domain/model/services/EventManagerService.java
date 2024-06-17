package by.zemich.vkms.domain.model.services;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.entities.Photo;
import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import by.zemich.vkms.domain.model.events.VkPostData;

import java.net.URI;
import java.util.List;

public class EventManagerService {
    public VkPostCreatedEvent createdEvent(VkPost vkPost) {


        List<String> postPhotoLinks = vkPost.getPhotos().getPhotos().stream()
                .map(Photo::getUri)
                .toList();

        VkPostData vkPostData = new VkPostData(
                postPhotoLinks,
                vkPost.getDateInfo().getPublishedAt(),
                vkPost.getText().getText()
        );

        VkPostCreatedEvent vkPostCreatedEvent = new VkPostCreatedEvent();
        vkPostCreatedEvent.setSupplierUuid(vkPost.getSupplier().getUuid());
        vkPostCreatedEvent.setUri(vkPost.getLinkToPost());
        vkPostCreatedEvent.setVkPostData(vkPostData);

        return vkPostCreatedEvent;
    }
}
