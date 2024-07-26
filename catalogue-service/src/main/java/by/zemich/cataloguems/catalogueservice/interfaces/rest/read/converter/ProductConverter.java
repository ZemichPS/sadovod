package by.zemich.cataloguems.catalogueservice.interfaces.rest.read.converter;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.ProductImage;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Supplier;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.Attribute;
import by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Image;
import by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConverter implements Converter<Product, ProductDto> {
    private static final Logger log = LoggerFactory.getLogger(ProductConverter.class);
    private final Converter<ProductImage, Image> imageConverter;
    private final Converter<Supplier, by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Supplier> supplierConverter;
    private final Converter<Attribute, by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Attribute> attributeConverter;

    public ProductConverter(Converter<ProductImage, Image> imageConverter, Converter<Supplier, by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Supplier> supplierConverter, Converter<Attribute, by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Attribute> attributeConverter) {
        this.imageConverter = imageConverter;
        this.supplierConverter = supplierConverter;
        this.attributeConverter = attributeConverter;
    }

    @Override
    public ProductDto convert(Product source) {

        List<by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Attribute> attributeList = source.getAttributes()
                .stream()
                .map(attributeConverter::convert)
                .toList();

        List<Image> imageList = source.getProductImageList()
                .stream()
                .map(imageConverter::convert)
                .toList();

        Supplier domainSupplier = source.getSupplier();
        by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response.Supplier supplier = supplierConverter.convert(domainSupplier);

        return ProductDto.builder()
                .uuid(source.getId().uuid())
                .name(source.getName().name())
                .postUuid(source.getPostId().postId())
                .category(source.getCategory().category())
                .originPrice(source.getPrice().originPrice())
                .availableInBulk(source.getAvailability().availableInBulk())
                .availableInPieces(source.getAvailability().availableInPieces())
                .quantityInPackage(source.getQuantityInSet().quantity())
                .supplier(supplier)
                .imageEntityList(imageList)
                .attributeList(attributeList)
                .build();
    }
}
