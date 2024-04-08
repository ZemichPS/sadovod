package by.zemich.supplierservice.application.ports.input;

import by.zemich.supplierservice.domain.supplier.model.Supplier;

import java.util.List;
import java.util.UUID;

public interface SupplierServicePort {
    Supplier save(Supplier newSupplier);
    Supplier update(UUID supplierUuid, Supplier fromRequestSupplier);
    List<Supplier> getAll();
    Supplier getByUuid(UUID supplierUuid);
    Supplier getByName(String supplierName);
    void delete(Supplier supplier);
    void deleteByUuid(UUID supplierUuid);
}
