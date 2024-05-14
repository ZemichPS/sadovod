package by.zemich.cataloguems.catalogueservice.domain.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductCardCommand {

    @NotNull(message = "PostUuid must not be null.")
    private UUID postUuid;
    @NotNull(message = "SupplierUuid must not be null.")
    private UUID supplierUuid;
    @NotNull(message = "Published date must not be null.")
    private LocalDateTime publishedAt;
    @NotNull(message = "Post text must not be null.")
    @NotBlank(message = "Post text must not be empty")
    private String postText;
    @NotNull(message = "Image list must not be null")
    @NotEmpty(message = "Image list must not be empty")
    private List<String> imagesLinkList;
    @NotNull(message = "Post uri must not be null.")
    @NotBlank(message = "Post uri must not be empty")
    private URL postUri;
}
