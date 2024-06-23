package by.zemich.cataloguems.catalogueservice.interfaces.rest.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ProductCardDto {
    private UUID uuid;
    private String productName;
    private String originalText;
    private LocalDateTime postPublishedAt;
    private UUID supplierUuid;
    private String materialName;
    private List<String> colorList;
    private boolean colorChoice;
    private boolean pictureChoice;
    private String sizeRange;
    private List<String> sizeList;
    private boolean inSize;
    private String quality;
}
