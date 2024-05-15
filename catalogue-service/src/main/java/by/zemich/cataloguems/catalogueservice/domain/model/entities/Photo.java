package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    private UUID uuid;
    @Column(name = "product_uuid")
    private UUID productUuid;
    private String link;

    public Photo(UUID uuid, UUID productUuid, String link) {
        this.uuid = uuid;
        this.productUuid = productUuid;
        this.link = link;
    }

    public Photo() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UUID getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid;
    }
}
