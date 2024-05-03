package by.zemich.vkms.domain.model.aggregates;

import by.zemich.vkms.domain.model.entities.Picture;
import by.zemich.vkms.domain.model.entities.SupplierId;
import by.zemich.vkms.domain.model.events.ActionEnum;
import by.zemich.vkms.domain.model.events.VkPostData;
import by.zemich.vkms.domain.model.events.VkPostUuid;
import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import by.zemich.vkms.domain.model.entities.FullPost;
import jakarta.persistence.*;
import org.apache.http.client.utils.URIBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;

@Entity
@Table(name = "posts", schema = "app")
public class VkPost extends AbstractAggregateRoot<VkPost> {
    @Id
    private java.util.UUID uuid;
    @Embedded
    private VkPostIdBKey vkPostBKey;

    @CreationTimestamp(source = SourceType.VM)
    private Timestamp createdAt;

    @Embedded
    private FullPost fullPost;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_uuid", referencedColumnName = "uuid")
    private SupplierId supplierId;

    public VkPost() {
    }

    public VkPost(CreateVkPostCommand command) {
        this.uuid = java.util.UUID.randomUUID();
        this.vkPostBKey = new VkPostIdBKey(command.getPostId(), command.getOwnerId());
        this.supplierId = new SupplierId(command.getSupplierUuid());
        this.fullPost = new FullPost()
                .setPublishedAt(command.getPublishedAt())
                .setImagesLinkList(command.getImagesLinkList().stream().map(uri -> new Picture(java.util.UUID.randomUUID(), uri.toString())).toList())
                .setText(command.getPostText());

        addDomainEvent(
                new VkPostCreatedEvent(
                        ActionEnum.CREATED,
                        new VkPostUuid(this.uuid),
                        new by.zemich.vkms.domain.model.events.VkPostId(this.vkPostBKey.getOriginalPostId(), this.vkPostBKey.getOwnerId()),
                        new VkPostData(this.fullPost.getImagesLinkList().stream().map(Picture::getUri).toList(), this.fullPost.getPublishedAt(), this.fullPost.getText()),
                        getLinkToPost()
                )
        );
    }


    private void addDomainEvent(Object event) {
        super.registerEvent(event);
    }

    public java.util.UUID getUuid() {
        return uuid;
    }

    public VkPostIdBKey getVkPostBKey() {
        return vkPostBKey;
    }

    public FullPost getFullPost() {
        return fullPost;
    }

    public SupplierId getSupplier() {
        return supplierId;
    }

    public URL getLinkToPost() {
        String host = "vk.com";
        String scheme = "https";

        try {
            return new URIBuilder().setScheme(scheme)
                    .setHost(host)
                    .setPath("/" + this.vkPostBKey.getOriginalPostId())
                    .addParameter("w", "wall" + this.vkPostBKey.getOwnerId() + "_" + this.vkPostBKey.getOriginalPostId())
                    .build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public Integer getDaysFromPublishedAt(){
        return Period.between(LocalDate.now(), this.fullPost.getPublishedAt().toLocalDate()).getDays();
    }


    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "VkPost{" +
                "uuid=" + uuid +
                ", vkPostBKey=" + vkPostBKey +
                ", createdAt=" + createdAt +
                ", fullPost=" + fullPost +
                ", supplierId=" + supplierId +
                '}';
    }
}
