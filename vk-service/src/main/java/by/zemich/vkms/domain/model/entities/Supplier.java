package by.zemich.vkms.domain.model.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Supplier {
    @Id
    private UUID uuid;
    private String name;
    private String vkId;
    private SupplierType type;

    public enum SupplierType {
        WOMEN_CLOTHES("женская одежда"),
        MAN_CLOTHES("мужская одежда"),
        OTHER("другое");

        SupplierType(String title) {
            this.title = title;
        }

        private final String title;

        public String getTitle() {
            return title;
        }
    }

    public Supplier() {
    }

    public Supplier(UUID uuid, String name, String vkId, SupplierType type) {
        this.uuid = uuid;
        this.name = name;
        this.vkId = vkId;
        this.type = type;
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

    public String getVkId() {
        return vkId;
    }

    public void setVkId(String vkId) {
        this.vkId = vkId;
    }

    public SupplierType getType() {
        return type;
    }

    public void setType(SupplierType type) {
        this.type = type;
    }
}
