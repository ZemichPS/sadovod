package by.zemich.testservice.application.internal;

import by.zemich.testservice.domain.events.LenovoEvent;
import by.zemich.testservice.infrastracture.brokers.kafka.TestServiceEventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@EnableBinding(TestServiceEventSource.class)
public class VkPostEventBrokerPublisherService {
    private final TestServiceEventSource testServiceEventSource;

    public VkPostEventBrokerPublisherService(TestServiceEventSource testServiceEventSource) {
        this.testServiceEventSource = testServiceEventSource;
    }

    @EventListener
    public void handleVkPostSavedEvent(LenovoEvent lenovoEvent) {
        log.info("Will be published {}", lenovoEvent.getSource());

        try {
            testServiceEventSource.output().send(
                    MessageBuilder.withPayload(lenovoEvent).build());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

    }


}
