package by.zemich.cataloguems.catalogueservice.domain.model.dto;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class ProductDescription {
    private  String name;
    private  String category;
    private BigDecimal price;
    private Integer quantityInSet;
    boolean availableInPieces;
    boolean availableInBulk;
    private List<Attribute> otherAttributes;


    public ProductDescription() {
    }

    public ProductDescription(String name,
                              String category,
                              BigDecimal price,
                              Integer quantityInSet,
                              boolean availableInPieces,
                              boolean availableInBulk,
                              List<Attribute> otherAttributes) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantityInSet = quantityInSet;
        this.availableInPieces = availableInPieces;
        this.availableInBulk = availableInBulk;
        this.otherAttributes = otherAttributes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantityInSet(Integer quantityInSet) {
        this.quantityInSet = quantityInSet;
    }

    public void setAvailableInPieces(boolean availableInPieces) {
        this.availableInPieces = availableInPieces;
    }

    public void setAvailableInBulk(boolean availableInBulk) {
        this.availableInBulk = availableInBulk;
    }

    public void setOtherAttributes(List<Attribute> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(this.name);
    }

    public Optional<String> getCategory() {
        return Optional.ofNullable(this.category);
    }

    public Optional<BigDecimal> getPrice() {
        return Optional.ofNullable(this.price);
    }

    public Optional<Integer> getQuantityInSet() {
        return Optional.ofNullable(this.quantityInSet);
    }

    public boolean isAvailableInPieces() {
        return availableInPieces;
    }

    public boolean isAvailableInBulk() {
        return availableInBulk;
    }

    public List<Attribute> getOtherAttributes() {
        return otherAttributes;
    }

    public static class Attribute{
        private String key;
        private String value;

        public Attribute() {
        }

        public Attribute(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
