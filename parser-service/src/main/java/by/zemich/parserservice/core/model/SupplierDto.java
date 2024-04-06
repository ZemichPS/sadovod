package by.zemich.parserservice.core.model;

import by.zemich.parserservice.core.enums.SupplierType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SupplierDto {
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String vkId;
    private String vkLink;
    private String address;
    private String phone;
    private SupplierType supplierType;
    @JsonBackReference
    private UserDto user;

}
