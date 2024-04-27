package by.zemich.vkms.infrastructure.adapters.repositories;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VkPostRepository extends CrudRepository<VkPost, UUID> {
    List<VkPost> findAll();
    Optional<VkPost> findByVkPostId(Integer postId);
    boolean existsByVkPostId(Integer postId);


}
