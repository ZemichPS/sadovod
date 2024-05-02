package by.zemich.vkms.infrastructure.brokers.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface VkEventSource {
    @Output("vkPostChannel")
    MessageChannel output();
}
