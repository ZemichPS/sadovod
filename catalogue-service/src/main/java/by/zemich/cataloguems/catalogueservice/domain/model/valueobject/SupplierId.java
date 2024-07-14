package by.zemich.cataloguems.catalogueservice.domain.model.valueobject;

import java.util.UUID;

public record SupplierId(UUID uuid) {
    public SupplierId withId(UUID uuid) {return new SupplierId(uuid);}
}
