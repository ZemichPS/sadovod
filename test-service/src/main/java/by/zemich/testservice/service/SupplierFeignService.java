package by.zemich.testservice.service;

import by.zemich.testservice.dto.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "gateway")
public interface SupplierFeignService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/suppliers")
    List<Supplier> getAll();

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/suppliers/{uuid}")
    Supplier getByUuid(UUID supplierUuid);

}
