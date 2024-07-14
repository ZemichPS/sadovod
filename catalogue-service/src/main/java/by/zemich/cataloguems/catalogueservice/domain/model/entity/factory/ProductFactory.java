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
                              UUID postId_,
                              List<String> imageLinks,
                              String postText,
                              Map<String, Object> productMap) {

        Supplier supplier = new Supplier();
        supplier.setId(new SupplierId(supplierUuid));
        supplier.setName(supplierName);

        ProductId productId = new ProductId(UUID.randomUUID());
        Type type = new Type((String) productMap.remove("product type"));
        Name name = new Name((String) productMap.remove("name"));
        Price price = new Price((BigDecimal) productMap.remove("price"), 643);
        PostId postId = new PostId(postId_);

        Availability availability = getAvailability(productMap);

        Product product = new Product(productId, supplier, type, name, price, postId, availability);
        getAnotherAttributes(productMap).forEach(product::addAttribute);
        return product;
    }

    public static Product get(ProductId productId,
                              Supplier supplier,
                              Type type,
                              Name name,
                              Price price,
                              List<ProductImage> images,
                              PostId postIdm,
                              Availability availability){

        Product product = new Product(productId,
                supplier,
                type,
                name,
                price,
                postIdm,
                availability
        );

        images.forEach(product::addImage);
        return product;
    }

    private static List<Attribute> getAnotherAttributes(Map<String, Object> keyValueMap) {
        return  keyValueMap.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    String value = (String) entry.getValue();
                    return new Attribute(key, value);
                }).toList();
    }

    //TODO сделать нормальный парсер
    private static Availability getAvailability(Map<String, Object> productMap){
        return new Availability(true, true, 4);
    }

}
