package by.zemich.uiservice.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Product {
    private UUID uuid;
    private String name;
    private UUID postUuid;
    private String category;
    private BigDecimal originPrice;
    private boolean availableInPieces;
    private boolean availableInBulk;
    private Integer quantityInPackage;
    private Supplier supplier;
    private List<Image> imageEntityList;
    private List<Attribute> attributeList;

    public record Image(UUID uuid, String link){}
    public record Attribute(String key, String value) {}
    public record Supplier(UUID uuid, String name) {}
}



