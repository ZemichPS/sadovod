package by.zemich.vkms.application.internal.queryservices;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.aggregates.VkPostIdBKey;
import by.zemich.vkms.domain.exception.VkPostNotFountException;
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

    public List<VkPostIdBKey> getAllPostIds() {
        return repository.findAll().stream()
                .map(VkPost::getVkPostBKey)
                .toList();
    }

    public VkPost find(UUID uuid) {
        return repository.findById(uuid).orElseThrow(VkPostNotFountException::new);
    }

    public VkPost findByPostBKey(VkPostIdBKey key) {
        return repository.findByVkPostBKey(key).orElseThrow(VkPostNotFountException::new);
    }
    public boolean existsByBKey(VkPostIdBKey key) {
        return repository.existsByVkPostBKey(key);
    }

    public VkPost findByUuid(UUID uuid) {
        return repository.findById(uuid).orElseThrow(VkPostNotFountException::new);
    }




}
