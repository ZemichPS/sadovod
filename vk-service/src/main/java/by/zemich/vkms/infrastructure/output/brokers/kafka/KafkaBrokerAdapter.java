package by.zemich.vkms.infrastructure.output.brokers.kafka;

import by.zemich.vkms.application.ports.output.PublishEventOutputPort;
import by.zemich.vkms.domain.model.events.VkPostDocument;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Log4j2
public class KafkaBrokerAdapter implements PublishEventOutputPort {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaBrokerAdapter(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @TransactionalEventListener
    public void publish(VkPostDocument event) {
        Map<String, Object> topicHeaders = new HashMap<>();
        topicHeaders.put(KafkaHeaders.TOPIC, "vk-post-topic");
        topicHeaders.put(KafkaHeaders.TIMESTAMP, Instant.now().toEpochMilli());
        topicHeaders.put(KafkaHeaders.KEY, event.getSupplierUuid().toString());
        final Message<VkPostDocument> message = MessageBuilder.withPayload(event).copyHeaders(topicHeaders).build();
        send(message);
    }

    private void send(Message<VkPostDocument> message) {
        CompletableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(message);
            } else {
                handleFailure(message, ex);
            }
        });
    }

    // TODO написать нормальный обработчик успешной отправки
    private void handleSuccess(Message<VkPostDocument> message) {
        log.info("event successfully has been sent. Id:{}", message.getPayload().getSupplierName());
    }

    // TODO написать нормальный обработчик неудачной отправки
    private void handleFailure(Message<VkPostDocument> message, Throwable exception) {
        log.error("event failed in sending. Id:{}. Exception: {}", message.getPayload().getSupplierName(),
                exception.getMessage());
    }



}
