package by.zemich.shareddomain.events;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class VkPostCreatedEvent {
    @NotNull
    private UUID supplierUuid;

    @NotEmpty
    private String supplierName;

    @NotNull
    @Size(min = 1) // Ensure there's at least one photo
    private List<Photo> photos;

    @NotEmpty
    private String linkToVkPost;

    @NotEmpty
    private String postText;

    record Photo(@NotEmpty String link) {
    }
}



