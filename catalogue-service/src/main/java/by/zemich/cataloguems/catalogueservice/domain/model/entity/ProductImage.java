package by.zemich.cataloguems.catalogueservice.domain.model.entity;

import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.ImageLink;
import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.ProductId;

import java.util.UUID;

public class ProductImage implements Cloneable{
    private UUID uuid;
    private ImageLink imageLink;
    private ProductId productId;

    public ProductImage(UUID uuid,
                        ImageLink imageLink,
                        ProductId productId) {
        this.uuid = uuid;
        this.imageLink = imageLink;
        this.productId = productId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ImageLink getImageLink() {
        return imageLink;
    }

    public ProductId getProductId() {
        return productId;
    }

    @Override
    public ProductImage clone() {
        try {
            return (ProductImage) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
