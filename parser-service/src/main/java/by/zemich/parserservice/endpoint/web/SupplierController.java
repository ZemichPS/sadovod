package by.zemich.parserservice.endpoint.web;

import by.zemich.parserservice.core.model.NewSupplierRequestDto;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.service.api.SupplierFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierFacade supplierFacade;

    public SupplierController(SupplierFacade supplierFacade) {
        this.supplierFacade = supplierFacade;
    }

    @PostMapping("/new")
    public ResponseEntity<SupplierDto> add(@RequestBody NewSupplierRequestDto newSupplierDto){
        SupplierDto createdSupplierDto = supplierFacade.create(newSupplierDto);
        return ResponseEntity.ok(createdSupplierDto);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<SupplierDto>> getAll(){
        List<SupplierDto> supplierDtoList = supplierFacade.getAll();
        return ResponseEntity.ok(supplierDtoList);

    }
}
