package by.zemich.vkms.domain.model.aggregates;

import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import by.zemich.shareddomain.events.VkPostCreatedEvent;
import by.zemich.vkms.domain.model.entities.FullPost;
import by.zemich.vkms.domain.model.entities.Supplier;
import by.zemich.shareddomain.events.VkPostCreatedEventData;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.UUID;

@Entity(name = "posts")
public class VkPost extends AbstractAggregateRoot<VkPost> {
    @Id
    private UUID uuid;
    @Embedded
    private VkPostId vkPostId;

    @Embedded
    private FullPost post;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Supplier supplier;

    public VkPost() {
    }

    public VkPost(CreateVkPostCommand command) {
        this.uuid = UUID.randomUUID();
        this.vkPostId = new VkPostId(command.getPostId(), command.getOwnerId());
        this.supplier = command.getSupplier();
        this.post = new FullPost()
                .setDate(command.getPublishedAt())
                .setLink(command.getPostLink())
                .setImagesLinkList(command.getImagesLinkList())
                .setText(command.getPostText());

        addDomainEvent(
                new VkPostCreatedEvent(
                        new VkPostCreatedEventData(this.uuid)
                )
        );
    }

    public void addDomainEvent(Object event) {
        super.registerEvent(event);
    }

    public UUID getUuid() {
        return uuid;
    }

    public VkPostId getVkPostId() {
        return vkPostId;
    }

    public FullPost getPost() {
        return post;
    }

    public Supplier getSupplier() {
        return supplier;
    }


}
