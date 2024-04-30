package by.zemich.vkms.infrastructure.adapters.brokers.kafka;

import by.zemich.shareddomain.events.VkPostCreatedEventData;
import by.zemich.vkms.domain.model.aggregates.VkPost;

public record VkPostSavedEvent(VkPostUuid uuid, VkPostId vkPostId, VkPostData vkPostData){};
