package by.zemich.aims.getproductdescription.impl;

import by.zemich.aims.getproductdescription.GetProductDescriptionRequest;
import by.zemich.aims.getproductdescription.GetProductDescriptionResponse;
import by.zemich.aims.getproductdescription.GetProductDescriptionServiceApi;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductDescriptionOpenAiService implements GetProductDescriptionServiceApi {

    private final OpenAiChatClient chatClient;

    public GetProductDescriptionOpenAiService(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public GetProductDescriptionResponse createJsonProductDescription(GetProductDescriptionRequest request) {
        List<Message> messages = buildPromptdMessages(request.getJsonDestination(), request.getSource());
        Prompt prompt = createPrompt(messages, buildChatOption("gpt-3.5-turbo", 0.4F));
        ChatResponse chatResponse = getChatResponse(prompt);
        String content = getChatResult(chatResponse);
        return new GetProductDescriptionResponse(content);
    }

    private List<Message> buildPromptdMessages(String systemRoleRequest, String userRoleRequest) {
       return List.of(
                new SystemMessage("""
                        You are a bot that converts product information from post to JSON. JSON values to russian.
                        JSON template: %s
                        """.formatted(systemRoleRequest)),
                new UserMessage("""
                        source: %s
                        """.formatted(userRoleRequest))
        );
    }

    private ChatOptions buildChatOption(String model, float temperature){
       return OpenAiChatOptions.builder()
                .withModel(model)
                .withTemperature(temperature)
                .build();
    }

    private Prompt createPrompt(List<Message> messages, ChatOptions chatOptions){
        return new Prompt(messages, chatOptions);
    }

    private ChatResponse getChatResponse(Prompt prompt){
        return chatClient.call(prompt);
    }

    private String getChatResult(ChatResponse chatResponse){
        return chatResponse.getResult().getOutput().getContent();
    }

}
