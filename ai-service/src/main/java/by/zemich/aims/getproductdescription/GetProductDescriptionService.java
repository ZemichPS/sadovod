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
                        You are a bot that converts product information from post to JSON.
                        The response must contain only JSON.
                        The response must be in strict accordance with the JSON template without any information.
                        Translate the value of the json fields into English and then into Russian.
                        JSON template: %s 
                        """.formatted(request.getJsonDestination()))
                .user("""
                        Source text: %s.
                        """.formatted(request.getSource())

                )
                .build();

log.info(request.getJsonDestination());

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
