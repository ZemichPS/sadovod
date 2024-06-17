package by.zemich.vkms.domain.model.entities;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Supplier {
    private UUID uuid;
    private String vkId;
    private String name;
}
