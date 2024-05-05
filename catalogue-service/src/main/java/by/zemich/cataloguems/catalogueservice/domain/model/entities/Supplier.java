package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    private UUID uuid;

    private String name;

    private String vkId;

    protected String address;
}
