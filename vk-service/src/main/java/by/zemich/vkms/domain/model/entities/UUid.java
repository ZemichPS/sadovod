package by.zemich.vkms.domain.model.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UUid {
    @Id
    private UUID uuid;

    public UUid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUid() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public static UUid of(UUID supplierUuid){
        return new UUid(supplierUuid);
    }
}
