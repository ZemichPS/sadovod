package by.zemich.cataloguems.catalogueservice.domain.model.entity;

import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private ProductId productId;

    private Supplier supplier;

    private Name name;

    private Category category;

    private Price price;

    private final PhotoAlbum photoAlbum;

    private PostId postId;

    private QuantityInSet quantityInSet;

    private Availability availability;

    private final List<Attribute> attributes = new ArrayList<>();

    public Product(ProductId productId) {
        this.productId = productId;
        this.photoAlbum = new PhotoAlbum();
    }

    public Product(ProductId productId, Supplier supplier, PhotoAlbum photoAlbum, PostId postId) {
        this.productId = productId;
        this.supplier = supplier;
        this.photoAlbum = photoAlbum;
        this.postId = postId;
    }

    public void addAttribute(Attribute attribute) {
        //TODO add check
        attributes.add(attribute);
    }

    public void addName(Name name) {
        //TODO add check
        this.name = name;
    }

    public void addPostId(PostId postId) {
        //TODO add check
        this.postId = postId;
    }



    public void addSupplier(Supplier supplier) {
        //TODO add check
        this.supplier = supplier;
    }

    public void addCategory(Category category) {
        //TODO add check
        this.category = category;
    }

    public void addQuantityInSet(QuantityInSet quantity) {
        //TODO add check
        this.quantityInSet = quantity;
    }

    public boolean addImage(ProductImage productImage) {
        //TODO add check
        return photoAlbum.addImage(productImage);
    }

    public void addPrice(Price price) {
        //TODO add check
        this.price = price;
    }

    public void addAvailability(Availability availability){
        //TODO add check
        this.availability = availability;
    }

    public List<ProductImage> getProductImageList() {
        return photoAlbum.getAllProductImages();
    }

    public List<Attribute> getAttributes() {
        return List.copyOf(attributes);
    }

    public ProductId getId() {
        return productId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Category getCategory() {
        return category;
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

    public QuantityInSet getQuantityInSet() {
        return quantityInSet;
    }
}
