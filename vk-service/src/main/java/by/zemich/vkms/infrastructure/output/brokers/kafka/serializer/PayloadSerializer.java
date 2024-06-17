package by.zemich.vkms.infrastructure.output.brokers.kafka.serializer;

import by.zemich.vkms.domain.model.events.VkPostCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Serializer;
import java.util.Map;

public class PayloadSerializer implements Serializer<VkPostCreatedEvent> {

    private final ObjectMapper mapper = new ObjectMapper();

    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, VkPostCreatedEvent event) {
        mapper.registerModule(new JavaTimeModule());

        if (event == null) {
            System.out.println("Null received at serializing");
            return null;
        }
        System.out.println("Serializing...");
        try {
            return mapper.writeValueAsBytes(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public void close() {

    }
}
