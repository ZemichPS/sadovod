package by.zemich.supplierservice.infrastructure.adapters.input.rest;

import by.zemich.supplierservice.application.ports.input.SupplierServicePort;
import by.zemich.supplierservice.domain.supplier.model.Supplier;
import by.zemich.supplierservice.infrastructure.adapters.input.rest.model.request.SupplierCreateRequest;
import by.zemich.supplierservice.infrastructure.adapters.input.rest.model.response.SupplierResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierRestAdapter {
    private final SupplierServicePort supplierServicePort;
    private final ModelMapper mapper;

    @GetMapping("/v1/api")
    public ResponseEntity<List<SupplierResponse>> getAll() {
        List<SupplierResponse> supplierResponseList = supplierServicePort.getAll().stream()
                .map(supplier -> mapper.map(supplier, SupplierResponse.class))
                .toList();

        return ResponseEntity.ok(supplierResponseList);
    }

    @GetMapping("/v1/api/{uuid}")
    public ResponseEntity<SupplierResponse> getByUuid(@PathVariable(name = "uuid") UUID uuid) {
        Supplier supplier = supplierServicePort.getByUuid(uuid);
        return ResponseEntity.ok(mapper.map(supplier, SupplierResponse.class));
    }

    @PostMapping("/v1/api")
    public ResponseEntity<SupplierResponse> create(@Valid @RequestBody SupplierCreateRequest createRequest) {
        Supplier newSupplier = mapper.map(createRequest, Supplier.class);
        supplierServicePort.save(newSupplier);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<SupplierResponse> update(@PathVariable UUID uuid, @Valid @RequestBody SupplierCreateRequest request) {
        Supplier supplierForUpdate = mapper.map(request, Supplier.class);
        Supplier updatedSupplier = supplierServicePort.update(uuid, supplierForUpdate);
        return ResponseEntity.ok(mapper.map(updatedSupplier, SupplierResponse.class));
    }

    @DeleteMapping("/v1/api/{uuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable(name = "uuid") UUID uuid) {
        supplierServicePort.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }


}
