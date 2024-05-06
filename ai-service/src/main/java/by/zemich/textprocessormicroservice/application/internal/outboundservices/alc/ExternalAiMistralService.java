package by.zemich.textprocessormicroservice.application.internal.outboundservices.alc;

import by.zemich.textprocessormicroservice.domain.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.dannyj.mistral.MistralClient;
import nl.dannyj.mistral.builders.MessageListBuilder;
import nl.dannyj.mistral.models.completion.ChatCompletionRequest;
import nl.dannyj.mistral.models.completion.ChatCompletionResponse;
import nl.dannyj.mistral.models.completion.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalAiMistralService {
    private final MistralClient client;
    private final ObjectMapper objectMapper;

    public ExternalAiMistralService(MistralClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public ProductInfo getProductInfoFromPostText(String text) {

        String jsonTemplate = getTemplate(buildDefaultObject());

        List<Message> messages = new MessageListBuilder()
                .system("Product text to JSON in Russian. Template: %S. Sizes and Colors are ArrayList. Otherwise write size to sizesRange variable".formatted(jsonTemplate))
                .user(text)
                .build();

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("open-mistral-7b")
                .temperature(0.1)
                .messages(messages)
                .safePrompt(false)
                .maxTokens(600)
                .build();

        ChatCompletionResponse response = client.createChatCompletion(request);
        Message message = response.getChoices().get(0).getMessage();
//        System.out.println("Total tokens: %s".formatted(response.getUsage().getTotalTokens()));
        return getProductInfoFromMessage(message);
    }

    private String getTemplate(ProductInfo template) {
        try {
            return objectMapper.writeValueAsString(template);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private ProductInfo buildDefaultObject() {
        return ProductInfo.builder()
                .withProductType("")
                .withColorInfo(new ColorInfo(new ArrayList<>(), true, true))
                .withMaterial(new Material("", ""))
                .withPrices(new Prices(Double.valueOf(0), Double.valueOf(0)))
                .withSizeInfo(new SizeInfo("", new ArrayList<>(), true))
                .withQuantityInSetOrBox(1)
                .build();
    }

    private ProductInfo getProductInfoFromMessage(Message receivedMessage) {
        try {
            return objectMapper.readValue(receivedMessage.getContent(), ProductInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
