package by.zemich.testservice.contoller;

import by.zemich.testservice.dto.Supplier;
import by.zemich.testservice.service.SupplierFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierFeignService supplierFeignService;

    public SupplierController(SupplierFeignService supplierFeignService) {
        this.supplierFeignService = supplierFeignService;
    }


    @GetMapping()
    ResponseEntity<List<Supplier>> getAll() {

        var suppliers = supplierFeignService.getAll();
        return ResponseEntity.ok(suppliers);
    }
}
