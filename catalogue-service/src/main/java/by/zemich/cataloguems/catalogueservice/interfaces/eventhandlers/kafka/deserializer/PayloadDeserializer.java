package by.zemich.cataloguems.catalogueservice.interfaces.eventhandlers.kafka.deserializer;

import by.zemich.shareddomain.events.VkPostDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Log4j2
public class PayloadDeserializer implements Deserializer<VkPostDocument> {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public VkPostDocument deserialize(String s, byte[] bytes) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            if (bytes == null) {
                log.error("Null received at deserializing");
                return null;
            }
            final VkPostDocument vkPostDocument = objectMapper.readValue(new String(bytes, "UTF-8"), VkPostDocument.class);
            return vkPostDocument;
        } catch (Exception e) {
            log.error("Error when deserializing byte[] to VkPostDocument. Exception: {}", e.getMessage());
            //throw new SerializationException("Error when deserializing byte[] to VkPostDocument");
            return null;
        }

    }

    @Override
    public void close() {

    }
}
