package by.zemich.supplierservice.interfaces.rest;

import by.zemich.supplierservice.application.ports.input.SupplierServicePort;
import by.zemich.supplierservice.domain.supplier.model.Supplier;
import by.zemich.supplierservice.interfaces.rest.model.request.SupplierCreateRequest;
import by.zemich.supplierservice.interfaces.rest.model.response.SupplierResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierRestAdapter {
    private final SupplierServicePort supplierServicePort;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll() {
        List<SupplierResponse> supplierResponseList = supplierServicePort.getAll().stream()
                .map(supplier -> mapper.map(supplier, SupplierResponse.class))
                .toList();

        return ResponseEntity.ok(supplierResponseList);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<SupplierResponse> getByUuid(@PathVariable(name = "uuid") UUID uuid) {
        Supplier supplier = supplierServicePort.getByUuid(uuid);
        return ResponseEntity.ok(mapper.map(supplier, SupplierResponse.class));
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody SupplierCreateRequest createRequest, UriComponentsBuilder ucb) {
        Supplier newSupplier = mapper.map(createRequest, Supplier.class);
        final Supplier savedSupplier = supplierServicePort.save(newSupplier);

        URI locationNewSupplier = ucb
                .path("supplier/v1/api/{id}")
                .buildAndExpand(savedSupplier.getUuid())
                .toUri();

        return ResponseEntity.created(locationNewSupplier).build();
    }

    public ResponseEntity<SupplierResponse> update(@PathVariable UUID uuid, @Valid @RequestBody SupplierCreateRequest request) {
        Supplier supplierForUpdate = mapper.map(request, Supplier.class);
        Supplier updatedSupplier = supplierServicePort.update(uuid, supplierForUpdate);
        return ResponseEntity.ok(mapper.map(updatedSupplier, SupplierResponse.class));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable(name = "uuid") UUID uuid) {
        supplierServicePort.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }


}
