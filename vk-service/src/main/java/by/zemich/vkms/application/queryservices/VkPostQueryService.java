package by.zemich.vkms.application.queryservices;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.aggregates.VkPostId;
import by.zemich.vkms.domain.model.exception.VkPostNotFountException;
import by.zemich.vkms.infrastructure.repositories.jpa.VkPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VkPostQueryService {
    private final VkPostRepository repository;
    public VkPostQueryService(VkPostRepository repository) {
        this.repository = repository;
    }

    public List<VkPost> findAll() {
        return repository.findAll();
    }

    public List<VkPostId> getAllPostIds() {
        return repository.findAll().stream()
                .map(VkPost::getVkPostId)
                .toList();
    }

    public VkPost find(UUID uuid) {
        return repository.findById(uuid).orElseThrow(VkPostNotFountException::new);
    }

    public VkPost findByPostId(Integer postId) {
        return repository.findByVkPostId(postId).orElseThrow(VkPostNotFountException::new);
    }

    public boolean existsById(Integer postId) {
        return repository.existsByVkPostId(postId);
    }


}
