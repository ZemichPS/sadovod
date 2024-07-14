package by.zemich.aims.getproductdescription.impl;

import by.zemich.aims.getproductdescription.GetProductDescriptionServiceApi;
import lombok.extern.log4j.Log4j2;
import nl.dannyj.mistral.MistralClient;
import nl.dannyj.mistral.builders.MessageListBuilder;
import nl.dannyj.mistral.models.completion.ChatCompletionRequest;
import nl.dannyj.mistral.models.completion.ChatCompletionResponse;
import nl.dannyj.mistral.models.completion.Message;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Order(2)
@Log4j2
class GetProductDescriptionMistralService implements GetProductDescriptionServiceApi {

    private final MistralClient client;

    GetProductDescriptionMistralService(MistralClient client) {
        this.client = client;
    }

    public String getProductDescription(String request) {
        List<Message> messages = new MessageListBuilder()
                .user("""
                        Source text: %s.
                        """.formatted(request)

                )
                .build();


        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("mistral-small-2402")
                .temperature(0.9)
                .messages(messages)
                .safePrompt(false)
                .maxTokens(500)
                .build();

        ChatCompletionResponse response = client.createChatCompletion(chatCompletionRequest);
        String content = response.getChoices()
                .getFirst()
                .getMessage()
                .getContent();

        log.info("input request: {}", request);
        log.info("output response: {}", content);
        log.info("Promt tokens token count {}: ", String.valueOf(response.getUsage().getPromptTokens()));
        log.info("Completion token count {}", String.valueOf(response.getUsage().getCompletionTokens()));
        log.info("Amount token count: {}", String.valueOf(response.getUsage().getTotalTokens()));

        return content;

    }

}
