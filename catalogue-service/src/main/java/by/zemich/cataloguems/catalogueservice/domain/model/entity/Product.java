package by.zemich.cataloguems.catalogueservice.domain.model.entity;

import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private ProductId productId;

    private Supplier supplier;

    private Type type;

    private Name name;

    private Price price;

    private PhotoAlbum photoAlbum;

    private PostId postId;

    private Availability availability;

    private final List<Attribute> attributes = new ArrayList<>();

    public Product(ProductId productId) {
        this.productId = productId;
    }

    public Product(ProductId productId,
                   Supplier supplier,
                   Type type,
                   Name name,
                   Price price,
                   PostId postId, Availability availability
    ) {
        this.productId = productId;
        this.supplier = supplier;
        this.type = type;
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.photoAlbum = new PhotoAlbum(new ArrayList<>());
        this.postId = postId;
    }

    public void addAttribute(Attribute attribute) {
        //TODO add check
        attributes.add(attribute);
    }

    public boolean addImage(ProductImage productImage) {
        //TODO add check
        return photoAlbum.addImage(productImage);
    }

    public List<ProductImage> getProductImageList() {
        return photoAlbum.getAllProductImages();
    }

    public List<Attribute> getAttributes(){
        return List.copyOf(attributes);
    }

    public ProductId getId() {
        return productId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public PostId getPostId() {
        return postId;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
