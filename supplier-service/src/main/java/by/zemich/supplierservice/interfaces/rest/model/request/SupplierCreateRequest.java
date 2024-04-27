package by.zemich.supplierservice.interfaces.rest.model.request;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SupplierCreateRequest {
    @NotBlank(message = "Field name cannot be empty")
    private String name;
    @NotBlank(message = "Field vk id cannot be empty")
    private String vkId;
    @NotBlank(message = "Field link on page cannot be empty")
    private String vkLink;
    @NotBlank(message = "Field address cannot be empty")
    private String address;

    private String phone;
    @NotBlank(message = "Field specialization cannot be empty")
    private String specialization;
}
