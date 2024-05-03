package by.zemich.vkms.interfaces.rest.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class VkPostResponse {
    private UUID uuid;
    private UUID supplierUuid;
    private LocalDateTime publishedAt;
    private Integer vkPostId;
    private Integer ownerId;
    private String text;
    private List<String> images;

    public VkPostResponse(UUID uuid,
                          UUID supplierUuid,
                          LocalDateTime publishedAt,
                          Integer vkPostId,
                          Integer ownerId,
                          String text,
                          List<String> images) {
        this.uuid = uuid;
        this.supplierUuid = supplierUuid;
        this.publishedAt = publishedAt;
        this.vkPostId = vkPostId;
        this.ownerId = ownerId;
        this.text = text;
        this.images = images;
    }

    public VkPostResponse() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public VkPostResponse setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public UUID getSupplierUuid() {
        return supplierUuid;
    }

    public VkPostResponse setSupplierUuid(UUID supplierUuid) {
        this.supplierUuid = supplierUuid;
        return this;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public VkPostResponse setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    public Integer getVkPostId() {
        return vkPostId;
    }

    public VkPostResponse setVkPostId(Integer vkPostId) {
        this.vkPostId = vkPostId;
        return this;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public VkPostResponse setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getText() {
        return text;
    }

    public VkPostResponse setText(String text) {
        this.text = text;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public VkPostResponse setImages(List<String> images) {
        this.images = images;
        return this;
    }
}
