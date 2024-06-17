package by.zemich.vkms.infrastructure.output.services.supplier;

import by.zemich.vkms.application.internal.ports.output.FetchSuppliersOutputPort;
import by.zemich.vkms.infrastructure.output.services.supplier.model.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient("SUPPLIER-SERVICE")
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

    @RequestMapping(method = RequestMethod.GET, value = "/supplier/v1/api")
    List<Supplier> getAll();

    @RequestMapping(method = RequestMethod.GET, value = "/supplier/v1/api/{uuid}")
    Supplier getByUuid(UUID supplierUuid);

}
