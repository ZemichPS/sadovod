package by.zemich.vkms.infrastructure.output.repositories.jpa.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vk_posts", schema = "app")
public class VkPostEntity {

    @Id
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "supplier_uuid", referencedColumnName = "uuid")
    private SupplierEntity supplier;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "vkPost"
    )
    private List<ImageEntity> imageEntityList;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime savedAt;

    private LocalDateTime publishedAt;

    private Integer originalPostId;

    private String text;
    private String hash;



    public VkPostEntity(UUID uuid,
                        LocalDateTime publishedAt,
                        LocalDateTime savedAt,
                        Integer originalPostId,
                        String text,
                        List<ImageEntity> imageEntityList,
                        SupplierEntity supplier, String hash) {
        this.uuid = uuid;
        this.publishedAt = publishedAt;
        this.savedAt = savedAt;
        this.originalPostId = originalPostId;
        this.text = text;
        this.imageEntityList = imageEntityList;
        this.supplier = supplier;
        this.hash = hash;
    }

    public VkPostEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }

    public Integer getOriginalPostId() {
        return originalPostId;
    }

    public void setOriginalPostId(Integer originalPostId) {
        this.originalPostId = originalPostId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ImageEntity> getImageEntityList() {
        return imageEntityList;
    }

    public void setImageEntityList(List<ImageEntity> imageEntityList) {
        this.imageEntityList = imageEntityList;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplierEntity) {
        this.supplier = supplierEntity;
    }

    @Override
    public String toString() {
        return "VkPostEntity{" +
                "uuid=" + uuid +
                ", publishedAt=" + publishedAt +
                ", savedAt=" + savedAt +
                ", originalPostId=" + originalPostId +
                ", text='" + text + '\'' +
                '}';
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}