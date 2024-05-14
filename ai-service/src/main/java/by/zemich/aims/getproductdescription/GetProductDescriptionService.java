package by.zemich.aims.getproductdescription;

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
class GetProductDescriptionService {

    private final MistralClient client;

    GetProductDescriptionService(MistralClient client) {
        this.client = client;
    }

    public GetProductDescriptionResponse createJsonProductDescription(GetProductDescriptionRequest request) {
        List<Message> messages = new MessageListBuilder()
                .system("""
                        Product description to JSON: %s. Response - only JSON, values in russian 
                        """.formatted(request.getJsonDestination()))
                .user(request.getSource())
                .build();


        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("open-mistral-7b")
                .temperature(0.1)
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
