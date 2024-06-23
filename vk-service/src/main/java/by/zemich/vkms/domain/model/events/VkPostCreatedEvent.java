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
public class VkPostCreatedEvent {
    private UUID SupplierUuid;
    private String SupplierName;
    private List<Photo> photos;
    private String linkToVkPost;
    private String postText;
}

record Photo(String link) {
}

