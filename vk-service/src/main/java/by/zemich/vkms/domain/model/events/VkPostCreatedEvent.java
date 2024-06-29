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
    private UUID supplierUuid;
    private String supplierName;
    private List<Photo> photos;
    private String linkToVkPost;
    private String postText;

    public record Photo(String link) {
    }
}



