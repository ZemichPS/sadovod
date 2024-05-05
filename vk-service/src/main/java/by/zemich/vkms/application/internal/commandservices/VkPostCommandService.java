package by.zemich.vkms.application.internal.commandservices;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.aggregates.VkPostIdBKey;
import by.zemich.vkms.domain.model.commands.CreateVkPostCommand;
import by.zemich.vkms.infrastructure.repositories.jpa.VkPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VkPostCommandService {
    private final VkPostRepository repository;

    public VkPostCommandService(VkPostRepository repository) {
        this.repository = repository;
    }

    public VkPostIdBKey createPost(CreateVkPostCommand command) {
        VkPost newVkPost = repository.save(new VkPost(command));
        System.out.println("command = " + command);
        System.out.println("post = " + newVkPost);
        return newVkPost.getVkPostBKey();
    }


}
