package by.zemich.shareddomain.events;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.net.URL;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class VkPostCreatedEvent implements Event{
    @NotNull(message = "PostUuid must not be null.")
    private VkPostUuid uuid;
    @NotNull(message = "SupplierUuid must not be null.")
    private UUID supplierUuid;
    private VkPostData vkPostData;
    @NotNull(message = "Post uri must not be null.")
    private URL uri;

 }
