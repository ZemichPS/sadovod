package by.zemich.vkservice.infrastructure.adapters.output.kafka;

import by.zemich.vkservice.application.ports.output.PostAsynchronousTransferPort;
import by.zemich.vkservice.domain.model.vkpost.VkPost;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaProducerAdapter implements PostAsynchronousTransferPort {
    @Override
    public void send(List<VkPost> post) {

    }
}
