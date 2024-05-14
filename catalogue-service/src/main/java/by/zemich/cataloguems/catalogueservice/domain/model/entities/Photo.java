package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Photo {
    @Id
    private UUID uuid;
    String link;

    public Photo(UUID uuid, String link) {
        this.uuid = uuid;
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
}
