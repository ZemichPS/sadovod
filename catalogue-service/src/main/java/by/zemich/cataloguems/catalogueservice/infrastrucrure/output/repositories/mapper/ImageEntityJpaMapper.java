package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.ProductImage;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.ImageLink;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.ProductId;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ImageEntity;

import java.util.UUID;

public class ImageEntityJpaMapper {
    public static ImageEntity imageDomainToEntity(ProductImage productImage){
        return ImageEntity.builder()
                .uuid(productImage.getUuid())
                .link(productImage.getImageLink().link())
                .build();
    }

    public static ProductImage entityToDomain(ImageEntity imageEntity){
        UUID imageUuid = imageEntity.getUuid();
        String imageLink = imageEntity.getLink();
        UUID productUuid  = imageEntity.getProduct().getUuid();
       return new ProductImage(imageUuid, new ImageLink(imageLink), new ProductId(productUuid));
    }
}
