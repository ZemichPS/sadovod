package by.zemich.supplierservice.domain.supplier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NewSupplierDto {
    private String name;
    private String vkId;
    private String vkLink;
    private String address;
    private String phone;
    private String specialization;

}
