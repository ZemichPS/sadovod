package by.zemich.vkms.infrastructure.repositories.jpa;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.aggregates.VkPostIdBKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VkPostRepository extends CrudRepository<VkPost, UUID> {
    List<VkPost> findAll();
    Optional<VkPost> findByVkPostBKey(VkPostIdBKey key);
    boolean existsByVkPostBKey(VkPostIdBKey key);

}
