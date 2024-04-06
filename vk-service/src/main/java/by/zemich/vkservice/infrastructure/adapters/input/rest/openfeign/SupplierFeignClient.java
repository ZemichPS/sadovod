package by.zemich.vkservice.infrastructure.adapters.input.rest.openfeign;

import by.zemich.vkservice.application.ports.input.SupplierInputPort;
import by.zemich.vkservice.domain.model.supplier.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient("supplier-service")
public interface SupplierFeignClient extends SupplierInputPort {
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<Supplier> getAll();
    @RequestMapping(method = RequestMethod.GET, value = "/uuid")
    Supplier getByUuid(UUID supplierUuid);

}
