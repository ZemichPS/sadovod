package by.zemich.vkms.domain.model.services;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.entities.Photo;
import by.zemich.vkms.domain.model.entities.Supplier;
import by.zemich.vkms.domain.model.events.VkPostDocument;

import java.util.List;

public class EventManagerService {
    public VkPostDocument createdEvent(VkPost vkPost) {

        Supplier supplier = vkPost.getSupplier();

        List<String> postPhotoLinks = vkPost.getPhotos().getPhotos().stream()
                .map(Photo::getUri)
                .toList();

        return VkPostDocument.builder()
                .supplierUuid(supplier.getUuid())
                .supplierName(supplier.getName())
                .imageLinks(postPhotoLinks)
                .postUuid(vkPost.getId().getUuid())
                .linkToVkPost(vkPost.getLinkToPost().toString())
                .postText(vkPost.getText().getText())
                .build();
    }
}
