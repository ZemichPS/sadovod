package by.zemich.vkms.domain.model.aggregates;

import by.zemich.vkms.domain.model.events.ActionEnum;
import by.zemich.vkms.domain.model.events.VkPostData;
import by.zemich.vkms.domain.model.events.VkPostUuid;
import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import by.zemich.vkms.domain.model.entities.FullPost;
import by.zemich.vkms.domain.model.entities.UUid;
import jakarta.persistence.*;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.*;
import java.util.UUID;

@Entity(name = "posts")
public class VkPost extends AbstractAggregateRoot<VkPost> {
    @Id
    private UUID uuid;
    @Embedded
    private VkPostId vkPostId;

    @Embedded
    private FullPost fullPost;

    @ManyToOne
    private UUid supplierUuid;

    public VkPost() {
    }

    public VkPost(CreateVkPostCommand command) {
        this.uuid = UUID.randomUUID();
        this.vkPostId = new VkPostId(command.getPostId(), command.getOwnerId());
        this.supplierUuid = command.getSupplier();
        this.fullPost = new FullPost()
                .setPublishedAt(command.getPublishedAt())
                .setImagesLinkList(command.getImagesLinkList())
                .setText(command.getPostText());

        addDomainEvent(
                new VkPostCreatedEvent(
                        ActionEnum.CREATED,
                        new VkPostUuid(this.uuid),
                        new by.zemich.vkms.domain.model.events.VkPostId(this.vkPostId.getVkPostId(), this.vkPostId.getOwnerId()),
                        new VkPostData(this.fullPost.getImagesLinkList(), this.fullPost.getPublishedAt(), this.fullPost.getText()),
                        getLinkToPost()
                )
        );
    }


    private void addDomainEvent(Object event) {
        super.registerEvent(event);
    }

    public UUID getUuid() {
        return uuid;
    }

    public VkPostId getVkPostId() {
        return vkPostId;
    }

    public FullPost getFullPost() {
        return fullPost;
    }

    public UUid getSupplier() {
        return supplierUuid;
    }

    public URL getLinkToPost() {
        String host = "vk.com";
        String scheme = "https";

        try {
            return new URIBuilder().setScheme(scheme)
                    .setHost(host)
                    .setPath("/" + this.vkPostId.getVkPostId())
                    .addParameter("w", "wall" + this.vkPostId.getOwnerId() + "_" + this.vkPostId.getVkPostId())
                    .build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public Integer getDaysFromPublishedAt(){
        return Period.between(LocalDate.now(), this.fullPost.getPublishedAt().toLocalDate()).getDays();
    }


}
