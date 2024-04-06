package by.zemich.vkservice.application.model;


import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VkPostDto {
    private UUID uuid;
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private String text;
    private List<String> imagesLinkList;
    private String link;
    private UUID supplierUuid;

}
