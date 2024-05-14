package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class SupplierUuid {
    private UUID id;

    public SupplierUuid(UUID id) {
        this.id = id;
    }

    public SupplierUuid() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
