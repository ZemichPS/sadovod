package by.zemich.testservice.contoller;

import by.zemich.testservice.dto.Supplier;
import by.zemich.testservice.service.SupplierFeignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierFeignService supplierFeignService;

    public SupplierController(SupplierFeignService supplierFeignService) {
        this.supplierFeignService = supplierFeignService;
    }

    @GetMapping()
    ResponseEntity<List<Supplier>> getByUuid(){
        var suppliers = supplierFeignService.getAll();
        return ResponseEntity.ok(suppliers);
    }
}
