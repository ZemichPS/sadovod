package by.zemich.vkms.domain.model.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Embeddable
public class FullPost {
    private List<URI> imagesLinkList;
    private LocalDateTime date;
    private String text;
    private URL link;

    public FullPost() {
    }

    public FullPost(List<URI> imagesLinkList, LocalDateTime date, String text, URL link) {
        this.imagesLinkList = imagesLinkList;
        this.date = date;
        this.text = text;
        this.link = link;
    }

    public FullPost setImagesLinkList(List<URI> imagesLinkList) {
        this.imagesLinkList = imagesLinkList;
        return this;
    }

    public FullPost setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public FullPost setText(String text) {
        this.text = text;
        return this;
    }

    public FullPost setLink(URL link) {
        this.link = link;
        return this;
    }

    @ElementCollection
    @CollectionTable(name = "full_post_links",
            joinColumns = @JoinColumn(name = "url_id"))
    public List<URI> getImagesLinkList() {
        return imagesLinkList;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public URL getLink() {
        return link;
    }
}
