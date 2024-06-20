package by.zemich.aims.getproductdescription.impl;

import by.zemich.aims.getproductdescription.GetProductDescriptionRequest;
import by.zemich.aims.getproductdescription.GetProductDescriptionResponse;
import by.zemich.aims.getproductdescription.GetProductDescriptionServiceApi;
import lombok.extern.log4j.Log4j2;
import nl.dannyj.mistral.MistralClient;
import nl.dannyj.mistral.builders.MessageListBuilder;
import nl.dannyj.mistral.models.completion.ChatCompletionRequest;
import nl.dannyj.mistral.models.completion.ChatCompletionResponse;
import nl.dannyj.mistral.models.completion.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
class GetProductDescriptionMistralService implements GetProductDescriptionServiceApi {

    private final MistralClient client;

    GetProductDescriptionMistralService(MistralClient client) {
        this.client = client;
    }

    public GetProductDescriptionResponse createJsonProductDescription(GetProductDescriptionRequest request) {
        List<Message> messages = new MessageListBuilder()
                .system("""
                        You are a bot that converts product information from post to JSON according to POJO: %s
                        The response must contain only JSON.
                        Values to russian;
                        """.formatted(request.getJsonDestination()))
                .user("""
                        Source text: %s.
                        """.formatted(request.getSource())

                )
                .build();

log.info(request.getJsonDestination());

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("mistral-small-2402")
                .temperature(0.9)
                .messages(messages)
                .safePrompt(false)
                .maxTokens(800)
                .build();

        ChatCompletionResponse response = client.createChatCompletion(chatCompletionRequest);
        String content = response.getChoices().get(0).getMessage().getContent();

        log.info("Promt tokens token count {}: ", String.valueOf(response.getUsage().getPromptTokens()));
        log.info("Completion token count {}", String.valueOf(response.getUsage().getCompletionTokens()));
        log.info("Amount token count: {}", String.valueOf(response.getUsage().getTotalTokens()));

        return new GetProductDescriptionResponse(content);

    }

}
