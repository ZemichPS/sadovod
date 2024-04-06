package by.zemich.supplierservice.application.service;

import by.zemich.supplierservice.application.ports.input.SupplierServicePort;
import by.zemich.supplierservice.application.ports.output.SupplierPersistencePort;
import by.zemich.supplierservice.domain.supplier.exception.SupplierNotFoundException;
import by.zemich.supplierservice.domain.supplier.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService implements SupplierServicePort {

    private final SupplierPersistencePort supplierPersistencePort;


    public SupplierService(SupplierPersistencePort supplierPersistencePort) {
        this.supplierPersistencePort = supplierPersistencePort;
    }

    @Override
    public Supplier save(Supplier newSupplier) {
        return supplierPersistencePort.save(newSupplier);
    }

    @Override
    public Supplier update(UUID supplierUuid, Supplier updateSupplier) {
        return supplierPersistencePort.save(updateSupplier);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierPersistencePort.getAll();
    }

    @Override
    public Supplier getByUuid(UUID supplierUuid) {
        return supplierPersistencePort.getByUuid(supplierUuid).orElseThrow(()-> new SupplierNotFoundException("Supplier not found"));
    }

    @Override
    public Supplier getByName(String supplierName) {
        return supplierPersistencePort.getByName(supplierName).orElseThrow(()-> new SupplierNotFoundException("Supplier not found"));
    }

    @Override
    public void delete(Supplier supplier) {
        supplierPersistencePort.delete(supplier);
    }

    @Override
    public void deleteByUuid(UUID supplierUuid) {
        supplierPersistencePort.deleteByUuid(supplierUuid);
    }
}
