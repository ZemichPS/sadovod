package by.zemich.vkms.application.internal.ports.output;

import by.zemich.vkms.domain.model.valueobjects.SupplierId;

import java.util.List;

public interface SupplierClientOutputPort {
    List<SupplierId> fetchSuppliersIds();
}
