package by.zemich.vkms.application.internal.outboundservices;

import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import org.springframework.messaging.Message;

public interface QueueBrokerPort {
    public void publish(Message<VkPostCreatedEvent> message);
}
