package by.zemich.parserservice.core.model;


import by.zemich.parserservice.core.enums.EProductType;
import lombok.*;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Builder
@Data
public class VkPostDto {
    private Integer id;
    private LocalDateTime publishedAt;
    private EProductType productType;
    private String text;
    private List<String> imagesLinkList;
    private SupplierDto supplierDto;
    private String link;



}
