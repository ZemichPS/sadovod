package by.zemich.vkms.domain.model.vkpost;


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
public class VkPost {
    private UUID uuid;
    private Integer id;
    private LocalDateTime publishedAt;
    private String text;
    private List<String> imagesLinkList;
    private String link;

}
