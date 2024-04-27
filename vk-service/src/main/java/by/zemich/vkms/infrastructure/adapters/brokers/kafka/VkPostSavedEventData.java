package by.zemich.vkms.infrastructure.adapters.brokers.kafka;

import by.zemich.vkms.domain.model.aggregates.VkPostId;

public record VkPostSavedEventData(VkPostId vkPostId) {
}
