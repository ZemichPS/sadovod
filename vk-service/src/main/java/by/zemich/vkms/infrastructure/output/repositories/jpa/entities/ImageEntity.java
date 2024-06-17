package by.zemich.vkms.infrastructure.output.repositories.jpa.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "images", schema = "app")
public class ImageEntity {
    @Id
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vk_post_uuid", referencedColumnName = "uuid")
    private VkPostEntity vkPost;

    private String uri;

    public ImageEntity(UUID uuid, VkPostEntity vkPost, String uri) {
        this.uuid = uuid;
        this.vkPost = vkPost;
        this.uri = uri;
    }

    public ImageEntity(UUID uuid, String uri) {
        this.uuid = uuid;
        this.uri = uri;
    }

    public ImageEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public VkPostEntity getVkPost() {
        return vkPost;
    }

    public void setVkPost(VkPostEntity vkPost) {
        this.vkPost = vkPost;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
