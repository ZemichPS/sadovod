package by.zemich.vkms.domain.model.commands;

import by.zemich.vkms.domain.model.entities.Supplier;

import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;


public class CreateVkPostCommand {
    private Supplier supplier;
    private Integer postId;
    private Integer ownerId;
    private List<URI> imagesLinkList;
    private String postText;
    private URL postLink;

    private LocalDateTime publishedAt;


    public CreateVkPostCommand(Supplier supplier,
                               Integer postId,
                               Integer ownerId,
                               List<URI> imagesLinkList,
                               String postText,
                               URL postLink, LocalDateTime publishDate) {
        this.supplier = supplier;
        this.postId = postId;
        this.ownerId = ownerId;
        this.imagesLinkList = imagesLinkList;
        this.postText = postText;
        this.postLink = postLink;
        this.publishedAt = publishDate;
    }

    public CreateVkPostCommand() {
    }

    public Supplier getSupplier() {
        return supplier;
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

    public URL getPostLink() {
        return postLink;
    }

    public CreateVkPostCommand setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public CreateVkPostCommand setPostLink(URL postLink) {
        this.postLink = postLink;
        return this;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public CreateVkPostCommand setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }
}
