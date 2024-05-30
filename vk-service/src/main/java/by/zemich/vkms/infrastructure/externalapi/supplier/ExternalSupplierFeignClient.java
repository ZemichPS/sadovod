package by.zemich.vkms.infrastructure.externalapi.supplier;

import by.zemich.vkms.application.internal.ports.output.SupplierClientOutputPort;
import by.zemich.vkms.infrastructure.externalapi.supplier.model.Supplier;
import by.zemich.vkms.domain.model.valueobjects.SupplierId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient("SUPPLIER-SERVICE")
public interface ExternalSupplierFeignClient extends SupplierClientOutputPort {
    default List<SupplierId> fetchSupplierIds() {
        return getAll().stream()
                .map(supplier -> new SupplierId(supplier.getVkId(), supplier.getUuid()))
                .toList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/supplier/v1/api")
    List<Supplier> getAll();

    @RequestMapping(method = RequestMethod.GET, value = "/supplier/v1/api/{uuid}")
    Supplier getByUuid(UUID supplierUuid);

}
