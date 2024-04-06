package by.zemich.vkservice.application.ports.input;

import by.zemich.vkservice.domain.model.supplier.Supplier;

import java.util.List;
import java.util.UUID;

public interface SupplierInputPort {
    List<Supplier> getAll();
    Supplier getByUuid(UUID supplierUuid);
}
