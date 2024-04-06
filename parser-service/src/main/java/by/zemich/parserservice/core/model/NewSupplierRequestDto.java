package by.zemich.parserservice.core.model;

import by.zemich.parserservice.core.enums.SupplierType;
import lombok.Data;

import java.util.UUID;

@Data
public class NewSupplierRequestDto {
    private String name;
    private String vkId;
    private String vkLink;
    private String address;
    private String phone;
    private SupplierType supplierType;
    private UUID userUuid;
}
