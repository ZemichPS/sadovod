package by.zemich.vkms.domain.model.aggregates;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class VkPostId {
    @Column(name = "vk_post_id")
    private Integer vkPostId;

    @Column(name = "owner_id")
    private Integer ownerId;

    public VkPostId() {
    }

    public VkPostId(Integer vkPostId, Integer ownerId) {
        this.vkPostId = vkPostId;
        this.ownerId = ownerId;
    }

    public Integer getVkPostId() {
        return vkPostId;
    }

    public void setVkPostId(Integer vkPostId) {
        this.vkPostId = vkPostId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VkPostId vkPostId = (VkPostId) o;
        return Objects.equals(vkPostId, vkPostId.vkPostId) && Objects.equals(ownerId, vkPostId.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vkPostId, ownerId);
    }
}
