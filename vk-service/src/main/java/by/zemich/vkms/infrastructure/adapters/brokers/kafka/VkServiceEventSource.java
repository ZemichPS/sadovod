package by.zemich.vkms.infrastructure.adapters.brokers.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface VkServiceEventSource {
    @Output("cargoBookingChannel")
    MessageChannel sadovodVkService();
}
