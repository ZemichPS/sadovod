package by.zemich.cataloguems.catalogueservice.domain.model.entity.factory;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Product;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.ProductImage;
import by.zemich.cataloguems.catalogueservice.domain.model.entity.Supplier;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProductFactory {
    public static Product get(UUID supplierUuid,
                              String supplierName,
                              UUID postId,
                              List<String> imageLinks
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
        return new Product(productId, supplier, photoAlbum, postIdVo);
    }
}
