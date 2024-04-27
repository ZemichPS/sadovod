package by.zemich.vkms.application.outboundservices;

import by.zemich.vkms.domain.model.vkpost.VkPost;
import by.zemich.vkms.infrastructure.adapters.brokers.kafka.VkPostSavedEvent;
import by.zemich.vkms.infrastructure.adapters.brokers.kafka.VkServiceEventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.annotation.*;
import org.springframework.transaction.event.TransactionalEventListener;


@Service
@EnableBinding(VkPost.class)
@Slf4j
public class VkPostEventPublisherService {
    private final VkServiceEventSource vkServiceEventSource;

    public VkPostEventPublisherService(VkServiceEventSource vkServiceEventSource) {
        this.vkServiceEventSource = vkServiceEventSource;
    }

    @TransactionalEventListener
    public void handleVkPostSavedEvent(VkPostSavedEvent vkPostSavedEvent) {
        try {
            vkServiceEventSource.sadovodVkService().send(
                    MessageBuilder.withPayload(vkPostSavedEvent).build());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

    }


}
