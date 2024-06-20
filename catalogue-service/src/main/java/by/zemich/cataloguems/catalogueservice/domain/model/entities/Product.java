package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import by.zemich.cataloguems.catalogueservice.domain.model.valueobjects.*;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;


public class Product {
    private ProductId id;

    @Getter
    @Setter
    private SupplierId supplierId;

    @Getter
    @Setter
    private ProductName name;

    private Description description;

    private Photos photos;

    private Category category;

    @Getter
    @Setter
    private Price price;

    private Link linkOnProduct;

    public static Predicate<Product> getProductBySupplierIdPredicate(SupplierId supplierId){
        return product -> product.getSupplierId().equals(supplierId);
    }

    public static Predicate<Product> getProductByProductNamePredicate(ProductName productName){
        return product -> product.getName().equals(productName);
    }

    public Product(ProductId id) {
        this.id = id;
    }

    public void addSupplierId(SupplierId supplierId){
        // TODO add specification
        this.supplierId = supplierId;
    }

    public void addProductName(ProductName productName){
        // TODO add specification
        this.name = productName;
    }

    public void addDescription(Description description){
        // TODO add specification
        this.description = description;
    }

    public void addPhotos(Photos photos){
        // TODO add specification
        this.photos = photos;
    }

    public void addPrice(Price price){
        // TODO add specification
        this.price = price;
    }

    public void addLink(Link link){
        // TODO add specification
        this.linkOnProduct = link;
    }

    public void addCategory(Category category){
        // TODO add specification
        this.category = category;
    }

}
