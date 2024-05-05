package by.zemich.vkms.infrastructure.brokers.kafka;

import by.zemich.vkms.application.internal.outboundservices.QueueBrokerPort;
import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Component
@Log4j2
public class KafkaBrokerAdapter implements QueueBrokerPort {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaBrokerAdapter(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(Message<VkPostCreatedEvent> message) {
        message.getHeaders().put(KafkaHeaders.TOPIC, "vk-post-topic");
        message.getHeaders().put(KafkaHeaders.TIMESTAMP, Timestamp.valueOf(LocalDateTime.now()));
        send(message);
    }

    private void send(Message<VkPostCreatedEvent> message) {
        CompletableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(message);
            }
            else {
                handleFailure(message, ex);
            }
        });
    }

    //TODO написать нормальный обработчик успешной отправки
    private void handleSuccess(Message<VkPostCreatedEvent> message){
        log.info("event successfully has been sent. Id:{}", message.getPayload().vkPostId());
    }

    //TODO написать нормальный обработчик неудачной отправки
    private void handleFailure(Message<VkPostCreatedEvent> message, Throwable exception){
        log.error("event failed in sending. Id: {}. Exception: {}", message.getPayload().vkPostId(), exception.getMessage());
    }
}
