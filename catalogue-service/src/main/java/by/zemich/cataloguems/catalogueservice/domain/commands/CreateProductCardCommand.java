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

    private UUID postUuid;
    private UUID supplierUuid;
    private LocalDateTime publishedAt;
    private String postText;
    private List<String> imagesLinkList;
    private URL postUri;
}
