package by.zemich.vkservice.application.ports.input;


import by.zemich.vkservice.application.model.VKPostQuery;
import by.zemich.vkservice.domain.model.vkpost.VkPost;

import java.util.List;

public interface VKServicePort {
    List<VkPost> getPosts(VKPostQuery query);
}
