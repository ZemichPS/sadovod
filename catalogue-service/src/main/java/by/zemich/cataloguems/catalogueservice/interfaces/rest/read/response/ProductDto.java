package by.zemich.cataloguems.catalogueservice.interfaces.rest.read.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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
}
