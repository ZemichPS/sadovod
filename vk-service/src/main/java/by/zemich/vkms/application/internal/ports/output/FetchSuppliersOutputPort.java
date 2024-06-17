package by.zemich.vkms.application.internal.ports.output;

import by.zemich.vkms.domain.model.entities.Supplier;

import java.util.List;

public interface FetchSuppliersOutputPort {
    List<Supplier> fetchSuppliers();
}
