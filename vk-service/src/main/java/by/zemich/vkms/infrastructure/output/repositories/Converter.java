package by.zemich.vkms.infrastructure.output.repositories;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.infrastructure.output.repositories.jpa.entities.ImageEntity;
import by.zemich.vkms.infrastructure.output.repositories.jpa.entities.VkPostEntity;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public VkPostEntity convertWithoutSupplier(VkPost someVkPost){

        VkPostEntity vkPostEntity = new VkPostEntity();
        vkPostEntity.setUuid(someVkPost.getId().getUuid());
        vkPostEntity.setOriginalPostId(someVkPost.getVkPostId().getOriginalPostId());
        vkPostEntity.setHash(someVkPost.getVkPostId().getHash());
        vkPostEntity.setPublishedAt(someVkPost.getDateInfo().getPublishedAt());
        vkPostEntity.setText(someVkPost.getText().getText());
        vkPostEntity.setImageEntityList(
                someVkPost.getPhotos().getPhotos().stream().map(
                        photo -> {
                            return new ImageEntity(photo.getUuid(), photo.getUri());
                        }
                ).toList()
        );

        vkPostEntity.getImageEntityList().forEach(
                imageEntity -> imageEntity.setVkPost(vkPostEntity)
        );

        return vkPostEntity;
    }

    public VkPost convert(VkPostEntity anyVkPostEntity){
        return new VkPost();
    }


}
