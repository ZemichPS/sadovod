package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.ProductImage;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ImageEntity;

public class ImageEntityJpaMapper {
    public static ImageEntity imageDomainToEntity(ProductImage productImage){
        return ImageEntity.builder()
                .uuid(productImage.getUuid())
                .link(productImage.getImageLink().link())
                .build();
    }
}
