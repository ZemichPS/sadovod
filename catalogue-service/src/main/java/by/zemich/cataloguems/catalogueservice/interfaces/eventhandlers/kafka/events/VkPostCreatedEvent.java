package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka.events;

import java.net.URL;

public record VkPostCreatedEvent(ActionEnum action, VkPostUuid uuid, VkPostId vkPostId, VkPostData vkPostData, URL uri){

};
