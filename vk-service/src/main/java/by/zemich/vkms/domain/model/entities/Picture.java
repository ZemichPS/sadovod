package by.zemich.vkms.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    private UUID uuid;

    private String uri;
    @Column(name = "post_uuid")
    private UUID postUuid;

    public Picture(UUID uuid, String uri, UUID postUuid) {
        this.uuid = uuid;
        this.uri = uri;
        this.postUuid = postUuid;
    }
    public Picture(UUID uuid, String uri) {
        this.uuid = uuid;
        this.uri = uri;
    }

    public Picture() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public UUID getPostUuid() {
        return postUuid;
    }

    public void setPostUuid(UUID postUuid) {
        this.postUuid = postUuid;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "uuid=" + uuid +
                ", uri='" + uri + '\'' +
                ", postUuid=" + postUuid +
                '}';
    }
}
