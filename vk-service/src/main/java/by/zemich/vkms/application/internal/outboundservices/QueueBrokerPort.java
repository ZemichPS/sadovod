package by.zemich.vkms.application.internal.outboundservices;

import by.zemich.vkms.domain.events.VkPostCreatedEvent;

public interface QueueBrokerPort {
    public void publish(VkPostCreatedEvent event);
}
