package by.zemich.vkms.application.outboundservices;

import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import by.zemich.vkms.infrastructure.brokers.kafka.VkEventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;


@Service
@Slf4j
@EnableBinding(VkEventSource.class)
public class VkPostEventPublisherService {
    private final VkEventSource vkEventSource;

    public VkPostEventPublisherService(VkEventSource vkEventSource) {
        this.vkEventSource = vkEventSource;
    }

    @TransactionalEventListener
    public void handleVkPostSavedEvent(VkPostCreatedEvent vkPostCreatedEvent) {
        try {
            vkEventSource.output().send(
                    MessageBuilder.withPayload(vkPostCreatedEvent).build());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

    }


}
