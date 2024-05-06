package by.zemich.vkms.application.internal.outboundservices;

import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;

public interface QueueBrokerPort {
    public void publish(VkPostCreatedEvent event);
}
