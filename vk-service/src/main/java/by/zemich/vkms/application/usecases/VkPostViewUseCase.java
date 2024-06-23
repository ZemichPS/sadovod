package by.zemich.vkms.application.usecases;

import by.zemich.vkms.domain.model.aggregates.VkPost;

import java.util.List;
import java.util.UUID;

public interface VkPostViewUseCase {
        VkPost retrieve(UUID uuid);



}
