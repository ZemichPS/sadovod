package by.zemich.vkms.domain.model.entities;

import jakarta.persistence.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Embeddable
public class FullPost {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_uuid")
    private List<Picture> imagesLinkList;
    private LocalDateTime publishedAt;
    private String text;

    public FullPost() {
    }

    public FullPost(List<Picture> imagesLinkList, LocalDateTime publishedAt, String text) {
        this.imagesLinkList = imagesLinkList;
        this.publishedAt = publishedAt;
        this.text = text;
    }

    public FullPost setImagesLinkList(List<Picture> imagesLinkList) {
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

    public List<Picture> getImagesLinkList() {
        return imagesLinkList;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "FullPost{" +
                "imagesLinkList=" + imagesLinkList +
                ", publishedAt=" + publishedAt +
                ", text='" + text + '\'' +
                '}';
    }
}
