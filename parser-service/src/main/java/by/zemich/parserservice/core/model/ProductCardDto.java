package by.zemich.parserservice.core.model;

import by.zemich.parserservice.core.enums.EProductType;
import lombok.Data;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ProductCardDto {
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private EProductType productType;
    private String productCategory;
    private String color;
    private String size;
    private String fabric;
    private String season;
    private String link;
    private boolean fleece;
    private boolean inSize;
    private boolean notInSize;
    private BigDecimal costPerSet;
    private BigDecimal costPerPeace;
    private boolean onlySetSale;
    private boolean sale;
    private List<String> imageLinks;
    private SupplierDto supplier;
    private BigDecimal releasePrice;

}
