package by.zemich.vkms.application.internal.ports.output;

import by.zemich.vkms.domain.model.aggregates.VkPost;

public interface VkPostManagementRepositoryOutputPort {
    VkPost savePost(VkPost newVkPost);

    boolean existsByHash(String hash);

}
