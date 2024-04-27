package by.zemich.supplierservice.interfaces.rest.model.response;

import by.zemich.supplierservice.domain.supplier.model.Supplier;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SupplierResponse {
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String vkId;
    private String vkLink;
    private String address;
    private String phone;
    private Supplier.Specialization specialization;

    public enum Specialization{
        DRESS, OTHER
    }
}
