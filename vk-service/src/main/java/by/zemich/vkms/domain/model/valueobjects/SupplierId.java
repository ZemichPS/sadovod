package by.zemich.vkms.domain.model.valueobjects;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SupplierId {
    private String supplierVkId;
    private UUID supplierUUID;
    private String name;
}
