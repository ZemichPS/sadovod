package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper;

import by.zemich.cataloguems.catalogueservice.domain.exception.PersistenceException;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ImageEntity;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ProductEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.util.List;

public class ProductJpaMapper {
    private final ObjectMapper objectMapper;

    public ProductJpaMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ProductEntity productDomainToEntity(Product product) throws PersistenceException{

        List<ImageEntity> imageEntities = product.getProductImageList()
                .stream()
                .map(ImageEntityJpaMapper::imageDomainToEntity)
                .toList();

        String attributes = null;
        try {
            attributes = objectMapper.writeValueAsString(product.getAttributes());
        } catch (JsonProcessingException e) {
            throw new PersistenceException("Failed to save product. Cause: %s".formatted(e.getCause()));
        }

        return ProductEntity
                .builder()
                .uuid(product.getId().uuid())
                .postUuid(product.getPostId().postId())
                .type(product.getType().type())
                .ISO4217CurrencyCode(product.getPrice().ISO4217CurrencyCode())
                .originPrice(product.getPrice().originPrice())
                .imageEntityList(imageEntities)
                .availableInPieces(product.getAvailability().availableInPieces())
                .availableInBulk(product.getAvailability().availableInBulk())
                .quantityInPackage(product.getQuantityInSet().quantity())
                .attributes(attributes)
                .build();
    }


}
