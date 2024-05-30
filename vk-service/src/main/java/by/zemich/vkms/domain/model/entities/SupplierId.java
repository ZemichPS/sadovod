package by.zemich.vkms.domain.model.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "suppliers", schema = "app")
public class Supplier {
    @Id
    private java.util.UUID uuid;
    private int vkId;
    private String name;

    public Supplier(UUID uuid, int vkId, String name) {
        this.uuid = uuid;
        this.vkId = vkId;
        this.name = name;
    }

    public Supplier() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getVkId() {
        return vkId;
    }

    public void setVkId(int vkId) {
        this.vkId = vkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
