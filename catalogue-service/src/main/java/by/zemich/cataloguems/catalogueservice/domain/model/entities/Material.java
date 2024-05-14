package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Material {
    @Id
    private UUID uuid;
    private String name;

    public Material(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public Material() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
