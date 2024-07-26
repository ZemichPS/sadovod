package by.zemich.cataloguems.catalogueservice.domain.model.entity.factory;

import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDescription;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.ProductImage;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Supplier;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProductFactory {
    public static Product get(UUID supplierUuid,
                              String supplierName,
                              UUID postId,
                              List<String> imageLinks,
                              ProductDescription productDescription
    ) {

        Supplier supplier = new Supplier();
        supplier.setId(new SupplierId(supplierUuid));
        supplier.setName(supplierName);
        ProductId productId = new ProductId(UUID.randomUUID());
        PostId postIdVo = new PostId(postId);

        PhotoAlbum photoAlbum = new PhotoAlbum();
        imageLinks.stream()
                .map(line -> new ProductImage(UUID.randomUUID(), new ImageLink(line), productId))
                .forEach(photoAlbum::addImage);

        Product product = new Product(productId, supplier, photoAlbum, postIdVo);

        Category category = productDescription.getCategory()
                .map(Category::new)
                .orElse(new Category("не определена"));

        Name name = productDescription.getName()
                .map(Name::new)
                .orElse(new Name(""));

        // TODO предусмотреть POLICY
        Price price = productDescription.getPrice()
                .map(value -> new Price(value, 643))
                .orElse(new Price(BigDecimal.ZERO, 643));

        // TODO предусмотреть POLICY
        QuantityInSet inSet = productDescription.getQuantityInSet()
                .map(QuantityInSet::new)
                .orElse(new QuantityInSet(1));

        // TODO предусмотреть POLICY
        Availability availability = new Availability(
                productDescription.isAvailableInPieces(),
                productDescription.isAvailableInBulk()
        );

        productDescription.getOtherAttributes()
                .stream()
                .map(attribute -> new Attribute(attribute.getKey(), attribute.getValue()))
                .forEach(product::addAttribute);


        product.addName(name);
        product.addCategory(category);
        product.addPrice(price);
        product.addQuantityInSet(inSet);
        product.addAvailability(availability);

        return product;
    }
}
