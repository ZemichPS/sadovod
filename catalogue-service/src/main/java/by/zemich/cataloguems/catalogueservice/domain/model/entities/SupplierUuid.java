package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class SupplierUuid {
    private UUID supplierUuid;

    public SupplierUuid(UUID supplierUuid) {
        this.supplierUuid = supplierUuid;
    }

    public SupplierUuid() {
    }

    public UUID getSupplierUuid() {
        return supplierUuid;
    }

    public void setSupplierUuid(UUID supplierUuid) {
        this.supplierUuid = supplierUuid;
    }
}
