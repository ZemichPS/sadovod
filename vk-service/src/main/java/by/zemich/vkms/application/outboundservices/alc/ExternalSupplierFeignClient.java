package by.zemich.vkms.application.outboundservices.alc;

import by.zemich.vkms.domain.model.entities.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient("supplier-service")
public interface ExternalSupplierFeignClient  {
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<Supplier> getAll();
    @RequestMapping(method = RequestMethod.GET, value = "/uuid")
    Supplier getByUuid(UUID supplierUuid);

}
