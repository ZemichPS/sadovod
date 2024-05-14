package by.zemich.vkms.domain.commands;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class CreateVkPostCommand {
    private UUID supplierUuid;
    private Integer postId;
    private Integer ownerId;
    private List<URI> imagesLinkList;
    private String postText;
    private LocalDateTime publishedAt;


    public CreateVkPostCommand(java.util.UUID supplierUuid,
                               Integer postId,
                               Integer ownerId,
                               List<URI> imagesLinkList,
                               String postText,
                               LocalDateTime publishDate) {
        this.supplierUuid = supplierUuid;
        this.postId = postId;
        this.ownerId = ownerId;
        this.imagesLinkList = imagesLinkList;
        this.postText = postText;
        this.publishedAt = publishDate;
    }

    public CreateVkPostCommand() {
    }

    public java.util.UUID getSupplierUuid() {
        return supplierUuid;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public List<URI> getImagesLinkList() {
        return imagesLinkList;
    }

    public String getPostText() {
        return postText;
    }

    public CreateVkPostCommand setSupplierUuid(java.util.UUID supplierUuid) {
        this.supplierUuid = supplierUuid;
        return this;
    }

    public CreateVkPostCommand setPostId(Integer postId) {
        this.postId = postId;
        return this;
    }

    public CreateVkPostCommand setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public CreateVkPostCommand setImagesLinkList(List<URI> imagesLinkList) {
        this.imagesLinkList = imagesLinkList;
        return this;
    }

    public CreateVkPostCommand setPostText(String postText) {
        this.postText = postText;
        return this;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public CreateVkPostCommand setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    @Override
    public String toString() {
        return "CreateVkPostCommand{" +
                "supplierUuid=" + supplierUuid +
                ", postId=" + postId +
                ", ownerId=" + ownerId +
                ", imagesLinkList=" + imagesLinkList +
                ", postText='" + postText + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
