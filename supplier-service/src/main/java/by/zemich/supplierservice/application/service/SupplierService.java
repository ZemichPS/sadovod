package by.zemich.supplierservice.application.service;

import by.zemich.supplierservice.application.ports.input.SupplierServicePort;
import by.zemich.supplierservice.application.ports.output.SupplierPersistencePort;
import by.zemich.supplierservice.domain.supplier.exception.SupplierNotFoundException;
import by.zemich.supplierservice.domain.supplier.model.Supplier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class SupplierService implements SupplierServicePort {

    private final SupplierPersistencePort supplierPersistencePort;

    public SupplierService(SupplierPersistencePort supplierPersistencePort) {
        this.supplierPersistencePort = supplierPersistencePort;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Supplier save(Supplier newSupplier) {
        newSupplier.setUuid(UUID.randomUUID());
        return supplierPersistencePort.save(newSupplier);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Supplier update(UUID supplierUuid, Supplier fromRequestSupplier) {
        Supplier supplierForUpdate = supplierPersistencePort.getByUuid(supplierUuid)
                .map(supplier -> {
                            supplier.setName(fromRequestSupplier.getName());
                            supplier.setPhone(fromRequestSupplier.getPhone());
                            supplier.setSpecialization(fromRequestSupplier.getSpecialization());
                            supplier.setVkId(fromRequestSupplier.getVkId());
                            supplier.setVkLink(fromRequestSupplier.getVkLink());
                            supplier.setAddress(fromRequestSupplier.getAddress());
                            return supplier;
                        }
                ).orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));

        return supplierPersistencePort.save(supplierForUpdate);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierPersistencePort.getAll();
    }

    @Override
    public Supplier getByUuid(UUID supplierUuid) {
        return supplierPersistencePort.getByUuid(supplierUuid).orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
    }

    @Override
    public Supplier getByName(String supplierName) {
        return supplierPersistencePort.getByName(supplierName).orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Supplier supplier) {
        supplierPersistencePort.delete(supplier);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteByUuid(UUID supplierUuid) {
        supplierPersistencePort.deleteByUuid(supplierUuid);
    }
}
