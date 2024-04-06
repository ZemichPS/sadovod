package by.zemich.vkservice.application.ports.output;

import by.zemich.vkservice.application.model.VkPostDto;
import by.zemich.vkservice.domain.model.vkpost.VkPost;

import java.util.List;
import java.util.UUID;

public interface PostPersistencePort {
    VkPost save(VkPost vkPost);
    VkPost update(VkPost vkPost);
    VkPost getByUuid(UUID uuid);
    List<VkPostDto> getAll();
    void delete(VkPost vkPost);
    void deleteByUuid(UUID uuid);
    
}
