package by.zemich.supplierservice.infrastructure.adapters.repositories.jpa;

import by.zemich.supplierservice.application.ports.output.SupplierPersistencePort;
import by.zemich.supplierservice.domain.supplier.exception.SupplierNotFoundException;
import by.zemich.supplierservice.domain.supplier.model.Supplier;
import by.zemich.supplierservice.infrastructure.adapters.repositories.jpa.entity.SupplierEntity;
import by.zemich.supplierservice.infrastructure.adapters.repositories.jpa.repository.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SupplierPersistenceAdapter implements SupplierPersistencePort {
    private final ModelMapper mapper;
    private final SupplierRepository supplierRepository;


    public SupplierPersistenceAdapter(ModelMapper mapper, SupplierRepository supplierRepository) {
        this.mapper = mapper;
        this.supplierRepository = supplierRepository;
    }


    @Override
    public Supplier save(Supplier newSupplier) {
        SupplierEntity supplierEntity = mapper.map(newSupplier, SupplierEntity.class);
        return mapper.map(supplierRepository.save(supplierEntity), Supplier.class);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll().stream()
                .map(entity -> mapper.map(entity, Supplier.class))
                .toList();
    }

    @Override
    public Optional<Supplier> getByUuid(UUID supplierUuid) {
        return supplierRepository.findById(supplierUuid)
                .map(entity -> mapper.map(entity, Supplier.class));
    }

    @Override
    public Optional<Supplier> getByName(String supplierName) {
        return supplierRepository.findByName(supplierName)
                .map(entity -> mapper.map(entity, Supplier.class));
    }

    @Override
    public void delete(Supplier supplier) {
        if (!supplierRepository.existsById(supplier.getUuid())) throw new SupplierNotFoundException("Supplier not found");
        SupplierEntity supplierEntity = mapper.map(supplier, SupplierEntity.class);
        supplierRepository.delete(supplierEntity);
    }

    @Override
    public void deleteByUuid(UUID supplierUuid) {
        if (!supplierRepository.existsById(supplierUuid)) throw new SupplierNotFoundException("Supplier not found");
        supplierRepository.deleteById(supplierUuid);
    }
}

