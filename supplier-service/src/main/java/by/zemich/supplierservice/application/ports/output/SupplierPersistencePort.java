package by.zemich.supplierservice.application.ports.output;

import by.zemich.supplierservice.domain.supplier.model.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierPersistencePort {
    Supplier save(Supplier newSupplier);
    List<Supplier> getAll();
    Optional<Supplier> getByUuid(UUID supplierUuid);
    Optional<Supplier> getByName(String supplierName);
    void delete(Supplier supplier);
    void deleteByUuid(UUID supplierUuid);
}
