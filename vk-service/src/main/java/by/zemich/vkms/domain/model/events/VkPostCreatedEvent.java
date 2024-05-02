package by.zemich.vkms.domain.model.events;

import java.net.URL;

public record VkPostCreatedEvent(ActionEnum action, VkPostUuid uuid, VkPostId vkPostId, VkPostData vkPostData, URL uri){

};
