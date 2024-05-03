package by.zemich.vkms.domain.model.aggregates;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class VkPostIdBKey {
    @Column(name = "original_post_id")
    private Integer originalPostId;

    @Column(name = "owner_id")
    private Integer ownerId;

    public VkPostIdBKey() {
    }

    public VkPostIdBKey(Integer originalPostId, Integer ownerId) {
        this.originalPostId = originalPostId;
        this.ownerId = ownerId;
    }

    public Integer getOriginalPostId() {
        return originalPostId;
    }

    public void setOriginalPostId(Integer vkPostId) {
        this.originalPostId = vkPostId;
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
        VkPostIdBKey that = (VkPostIdBKey) o;
        return Objects.equals(originalPostId, that.originalPostId) && Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalPostId, ownerId);
    }
}
