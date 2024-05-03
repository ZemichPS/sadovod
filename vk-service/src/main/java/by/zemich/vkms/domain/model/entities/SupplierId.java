package by.zemich.vkms.domain.model.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers", schema = "app")
public class SupplierId {
    @Id
    private java.util.UUID uuid;

    public SupplierId(java.util.UUID uuid) {
        this.uuid = uuid;
    }

    public SupplierId() {
    }

    public java.util.UUID getUuid() {
        return uuid;
    }

    public void setUuid(java.util.UUID uuid) {
        this.uuid = uuid;
    }

    public static SupplierId of(java.util.UUID supplierUuid){
        return new SupplierId(supplierUuid);
    }
}
