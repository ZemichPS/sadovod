package by.zemich.gatewayservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public final class PageSerializationUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private PageSerializationUtil() {
    }

    public static <T> String serialize(Page<T> page) throws JsonProcessingException {
        return objectMapper.writeValueAsString(page);
    }

    public static <T> Page<T> deserialize(String json, Class<T> clazz) throws JsonProcessingException {
        TypeReference<PageImpl<T>> typeReference = new TypeReference<PageImpl<T>>() {};
        return objectMapper.readValue(json, typeReference);
    }
}
