package by.zemich.cataloguems.catalogueservice.domain.model.entities;

import jakarta.persistence.Embeddable;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
public class VkPost {
    private UUID postUuid;
    private String originalText;
    private LocalDateTime postPublishedAt;
    private URL url;

    public VkPost(UUID postUuid, String originalText, LocalDateTime postPublishedAt, URL url) {
        this.postUuid = postUuid;
        this.originalText = originalText;
        this.postPublishedAt = postPublishedAt;
        this.url = url;
    }

    public VkPost() {
    }

    public UUID getPostUuid() {
        return postUuid;
    }

    public void setPostUuid(UUID uuid) {
        this.postUuid = uuid;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String sourceDescription) {
        this.originalText = sourceDescription;
    }

    public LocalDateTime getPostPublishedAt() {
        return postPublishedAt;
    }

    public void setPostPublishedAt(LocalDateTime postPublishedAt) {
        this.postPublishedAt = postPublishedAt;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
