package by.zemich.testservice.infrastracture.brokers.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestServiceEventSource {
    @Output("testEvent")
    MessageChannel output();
}
