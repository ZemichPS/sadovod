package by.zemich.vkms.infrastructure.adapters.eventhandlers.kafka;

import by.zemich.vkms.domain.model.vkpost.VkPost;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaProducerAdapter {
    private final KafkaTemplate<Integer, VkPost> kafkaTemplate;

    public KafkaProducerAdapter(KafkaTemplate<Integer, VkPost> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(List<VkPost> postList) {
        postList.forEach(
                post -> {
                    ProducerRecord<Integer, VkPost> record = new ProducerRecord<>("vkpost", post);
                    CompletableFuture<SendResult<Integer, VkPost>> completeResult = kafkaTemplate.send(record);
                    completeResult.whenComplete(((result, exception) -> {
                        if(exception == null) {
                            handleSuccess(result);
                        } else {
                            handleFailure(exception, post);
                        }
                    }));
                }
        );
    }

    private void handleSuccess(SendResult<Integer, VkPost> result){
        log.info("Record has been successfully sent. ");
    }

    private void handleFailure(Throwable exception, VkPost vkPost){

    }
}
