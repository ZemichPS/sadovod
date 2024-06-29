package by.zemich.vkms.infrastructure.output.services.supplier;

import by.zemich.vkms.application.ports.output.FetchSuppliersOutputPort;
import by.zemich.vkms.infrastructure.output.services.supplier.model.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "supplier-service")
public interface ExternalFetchSuppliers extends FetchSuppliersOutputPort {
    default List<by.zemich.vkms.domain.model.entities.Supplier> fetchSuppliers() {
        return getAll().stream()
                .map(supplier -> new by.zemich.vkms.domain.model.entities.Supplier(
                        supplier.getUuid(),
                        supplier.getVkId(),
                        supplier.getName()
                ))
                .toList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/suppliers")
    List<Supplier> getAll();

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/suppliers/{uuid}")
    Supplier getByUuid(UUID supplierUuid);

}
