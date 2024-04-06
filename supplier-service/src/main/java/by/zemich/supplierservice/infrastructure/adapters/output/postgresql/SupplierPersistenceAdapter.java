package by.zemich.supplierservice.infrastructure.adapters.output.postgresql;

import by.zemich.supplierservice.application.ports.output.SupplierPersistencePort;
import by.zemich.supplierservice.domain.supplier.model.Supplier;
import by.zemich.supplierservice.infrastructure.adapters.output.postgresql.repository.SupplierRepository;
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
        return null;
    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

    @Override
    public Optional<Supplier> getByUuid(UUID supplierUuid) {
        return Optional.empty();
    }

    @Override
    public Optional<Supplier> getByName(String supplierName) {
        return Optional.empty();
    }

    @Override
    public void delete(Supplier supplier) {

    }

    @Override
    public void deleteByUuid(UUID supplierUuid) {

    }
}

