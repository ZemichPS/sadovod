package by.zemich.vkservice.infrastructure.adapters.output.postgresql.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "posts")
public class VkPostEntity {
    @Id
    private UUID uuid;
    private Integer id;
    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;
    @UpdateTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;
    private Timestamp publishedAt;
    private String text;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> imagesLinkList;
    private String link;
    private UUID supplierUuid;

    public VkPostEntity(UUID uuid,
                        Integer id,
                        Timestamp createdAt, Timestamp updatedAt, Timestamp publishedAt,
                        String text, List<String> imagesLinkList,
                        String link, UUID supplierUuid) {
        this.uuid = uuid;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.text = text;
        this.imagesLinkList = imagesLinkList;
        this.link = link;
        this.supplierUuid = supplierUuid;
    }

    public VkPostEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImagesLinkList() {
        return imagesLinkList;
    }

    public void setImagesLinkList(List<String> imagesLinkList) {
        this.imagesLinkList = imagesLinkList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UUID getSupplierUuid() {
        return supplierUuid;
    }

    public void setSupplierUuid(UUID supplierUuid) {
        this.supplierUuid = supplierUuid;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
