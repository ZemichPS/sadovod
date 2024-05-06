package by.zemich.vkms.application.internal.outboundservices;

import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;


@Service
@Slf4j
public class VkPostEventPublisherService {
    private final QueueBrokerPort queueBrokerPort;

    public VkPostEventPublisherService(QueueBrokerPort queueBrokerPort) {
        this.queueBrokerPort = queueBrokerPort;
    }

    @TransactionalEventListener
    public void handleVkPostSavedEvent(VkPostCreatedEvent vkPostCreatedEvent) {
        try {
            queueBrokerPort.publish(vkPostCreatedEvent);

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

    }


}
