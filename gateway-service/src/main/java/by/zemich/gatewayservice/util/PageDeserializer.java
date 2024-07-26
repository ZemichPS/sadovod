package by.zemich.gatewayservice.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.List;

public class PageDeserializer extends StdDeserializer<Page<?>> {

    public PageDeserializer() {
        super((Class<Page<?>>) null);
    }

    @Override
    public Page<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int totalPages = node.get("totalPages").intValue();
        long totalElements = node.get("totalElements").longValue();
        int number = node.get("number").intValue();
        int size = node.get("size").intValue();
        List<?> content = jsonParser.getCodec().treeToValue(node.get("content"), List.class);
        return new PageImpl<>(content, PageRequest.of(number, size), totalElements);
    }
}
