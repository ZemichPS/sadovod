package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka;

import by.zemich.shareddomain.events.VkPostCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaEventHandler {
    @KafkaListener(topics = "vk-post-topic", id = "vk-post-service-group")
    public void handle(Message<VkPostCreatedEvent> message) {
        log.info("Message has been received: {}", message.getPayload());
    }

}
