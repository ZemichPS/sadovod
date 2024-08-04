package by.zemich.gatewayservice.model.dto;

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

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDto {
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
    private boolean sale;
}
