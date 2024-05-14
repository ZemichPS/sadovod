package by.zemich.shareddomain.events;


import lombok.*;

import java.net.URL;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class VkPostCreatedEvent {
    private VkPostUuid uuid;
    private UUID supplierUuid;
    private VkPostId vkPostId;
    private VkPostData vkPostData;
    private URL uri;

 }
