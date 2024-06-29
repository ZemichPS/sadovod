package by.zemich.vkms.domain.model.services;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.entities.Photo;
import by.zemich.vkms.domain.model.entities.Supplier;
import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;

import java.util.List;

public class EventManagerService {
    public VkPostCreatedEvent createdEvent(VkPost vkPost) {

        Supplier supplier = vkPost.getSupplier();

        List<VkPostCreatedEvent.Photo> postPhotoLinks = vkPost.getPhotos().getPhotos().stream()
                .map(Photo::getUri)
                .map(VkPostCreatedEvent.Photo::new)
                .toList();

        return VkPostCreatedEvent.builder()
                .supplierUuid(supplier.getUuid())
                .supplierName(supplier.getName())
                .photos(postPhotoLinks)
                .linkToVkPost(vkPost.getLinkToPost().toString())
                .postText(vkPost.getText().getText())
                .build();
    }
}
