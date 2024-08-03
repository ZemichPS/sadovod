package by.zemich.vkms.domain.model.events;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class VkPostDocument {
    private UUID supplierUuid;
    private UUID postUuid;
    private String supplierName;
    private List<String> imageLinks;
    private String linkToVkPost;
    private String postText;
}



