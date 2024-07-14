package by.zemich.cataloguems.catalogueservice.domain.model.entity;

import by.zemich.cataloguems.catalogueservice.domain.model.valueobject.SupplierId;

public class Supplier {
    private SupplierId id;
    private String name;

    public Supplier() {
    }

    public Supplier(SupplierId id, String name) {
        this.id = id;
        this.name = name;
    }

    public SupplierId getId() {
        return id;
    }

    public void setId(SupplierId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
