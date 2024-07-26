package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper;

import by.zemich.cataloguems.catalogueservice.domain.exception.PersistenceException;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Supplier;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.*;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ImageEntity;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.ProductEntity;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.SupplierEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ProductJpaMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ProductJpaMapper() {
    }

    public static ProductEntity productDomainToEntity(Product product) throws PersistenceException, JsonProcessingException {

        final List<Attribute> attributeList = product.getAttributes();
        String attributes = attributesEntityToAttributesDomain(attributeList);

        return ProductEntity
                .builder()
                .uuid(product.getId().uuid())
                .postUuid(product.getPostId().postId())
                .name(product.getName().name())
                .category(product.getCategory().category())
                .ISO4217CurrencyCode(product.getPrice().ISO4217CurrencyCode())
                .originPrice(product.getPrice().originPrice())
                .availableInPieces(product.getAvailability().availableInPieces())
                .availableInBulk(product.getAvailability().availableInBulk())
                .imageEntityList(new ArrayList<>())
                .quantityInPackage(product.getQuantityInSet().quantity())
                .attributes(attributes)
                .build();
    }

    public static Product entityToDomain(ProductEntity productEntity) throws JsonProcessingException {
        String attributes = productEntity.getAttributes();
        List<Attribute> attributeList = attributeToList(attributes);

        SupplierEntity supplierEntity = productEntity.getSupplier();
        Supplier supplier = SupplierJpaMapper.entityToSupplierDomain(supplierEntity);

        Availability availability = new Availability(productEntity.isAvailableInPieces(), productEntity.isAvailableInBulk());

        Product product = new Product(new ProductId(productEntity.getUuid()));
        product.addSupplier(supplier);
        product.addName(new Name(productEntity.getName()));
        product.addCategory(new Category(productEntity.getCategory()));
        product.addPrice(new Price(productEntity.getOriginPrice(), productEntity.getISO4217CurrencyCode()));

        product.addQuantityInSet(new QuantityInSet(productEntity.getQuantityInPackage()));
        productEntity.getImageEntityList().stream()
                .map(ImageEntityJpaMapper::entityToDomain)
                .forEach(product::addImage);
        attributeList.forEach(product::addAttribute);
        product.addAvailability(availability);
        product.addPostId(new PostId(productEntity.getPostUuid()));
        return product;
    }


    private static List<Attribute> attributeToList(String json) throws PersistenceException, JsonProcessingException {
        if (json == null || (json.isEmpty())) return Collections.emptyList();
        return objectMapper.readValue(json, new TypeReference<List<Attribute>>() {});
    }

    private static String attributesEntityToAttributesDomain(List<Attribute>  attributes) throws JsonProcessingException {
        return objectMapper.writeValueAsString(attributes);
    }



}



