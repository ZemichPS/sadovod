package by.zemich.vkms.domain.model.commands;

import lombok.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateVkPostCommand {
    private UUID supplierUuid;
    private Integer SupplierVkId;
    private String supplierName;
    private Integer postId;
    private Integer ownerId;
    private List<URI> imagesLinkList;
    private String postText;
    private LocalDateTime publishedAt;

}
