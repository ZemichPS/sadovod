package by.zemich.vkms.application.queryservices;

import by.zemich.vkms.application.outboundservices.alc.ExternalSupplierFeignClient;
import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.aggregates.VkPostId;
import by.zemich.vkms.domain.model.entities.Supplier;
import by.zemich.vkms.domain.model.exception.VkPostNotFountException;
import by.zemich.vkms.infrastructure.adapters.repositories.VkPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VkPostQueryService {
    private final VkPostRepository repository;
    private final ExternalSupplierFeignClient supplierFeignClient;
    public VkPostQueryService(VkPostRepository repository, ExternalSupplierFeignClient supplierFeignClient) {
        this.repository = repository;
        this.supplierFeignClient = supplierFeignClient;
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

    public VkPost findByPosTId(Integer postId) {
        return repository.findByVkPostId(postId).orElseThrow(VkPostNotFountException::new);
    }

    public boolean existsById(Integer postId) {
        return repository.existsByVkPostId(postId);
    }

    public List<Supplier> getSuppliers(){
        return supplierFeignClient.getAll();
    }
}
