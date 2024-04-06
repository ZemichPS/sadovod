package by.zemich.vkservice.application.ports.output;

import by.zemich.vkservice.application.model.VkPostDto;
import by.zemich.vkservice.domain.model.vkpost.VkPost;

import java.util.List;

public interface PostAsynchronousTransferPort {
    void send(List<VkPost> post);
}
