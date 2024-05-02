package by.zemich.vkms.domain.model.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Embeddable
public class FullPost {
//    @CollectionTable(name = "full_post_links",
//            joinColumns = @JoinColumn(name = "url_id"))
    @ElementCollection
    private List<URI> imagesLinkList;
    private LocalDateTime publishedAt;
    private String text;


    public FullPost() {
    }

    public FullPost(List<URI> imagesLinkList, LocalDateTime publishedAt, String text) {
        this.imagesLinkList = imagesLinkList;
        this.publishedAt = publishedAt;
        this.text = text;
    }

    public FullPost setImagesLinkList(List<URI> imagesLinkList) {
        this.imagesLinkList = imagesLinkList;
        return this;
    }

    public FullPost setPublishedAt(LocalDateTime date) {
        this.publishedAt = date;
        return this;
    }

    public FullPost setText(String text) {
        this.text = text;
        return this;
    }


    public List<URI> getImagesLinkList() {
        return imagesLinkList;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getText() {
        return text;
    }


}
