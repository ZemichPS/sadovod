package by.zemich.textprocessormicroservice.interfaces.rest;

import by.zemich.textprocessormicroservice.interfaces.rest.request.ItemTemplate;
import by.zemich.textprocessormicroservice.interfaces.rest.request.Material;
import by.zemich.textprocessormicroservice.interfaces.rest.request.Prices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.dannyj.mistral.MistralClient;
import nl.dannyj.mistral.builders.MessageListBuilder;
import nl.dannyj.mistral.models.completion.ChatCompletionRequest;
import nl.dannyj.mistral.models.completion.ChatCompletionResponse;
import nl.dannyj.mistral.models.completion.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ai")
public class TestController {

    private final MistralClient client = new MistralClient("9PkC2c0r7Dh5SI3q9ZBCbKhT14dVvGt5");
    private final ObjectMapper objectMapper;

    public TestController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/v1/api/test")
    public ResponseEntity<String> test(@RequestParam String text, @RequestParam Double temperature) {
        String model = "open-mistral-7b";
      //  String model = "open-mixtral-8x7b";

        text = prepareTextPost(text);

        System.out.printf("Prepared text: %s%n", text);

        List<Message> messages = new MessageListBuilder()
                .system("User text to JSON in Russian. Template: " + getTemplate() + ". Sizes and Colors are ArrayList.")
                .user(text)
                .build();

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model(model)
                .temperature(temperature)
                .messages(messages)
                .safePrompt(false)
                .maxTokens(150)
                .build();

        ChatCompletionResponse response = client.createChatCompletion(request);
        Message firstChoice = response.getChoices().get(0).getMessage();

        try {
            ItemTemplate template = objectMapper.readValue(firstChoice.getContent(), ItemTemplate.class);
            System.out.println(template);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total tokens: %s".formatted(response.getUsage().getTotalTokens()));
        return ResponseEntity.ok(firstChoice.getContent());

    }


    private String getTemplate() {
        try {
            return objectMapper.writeValueAsString(new ItemTemplate());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String prepareTextPost(String text) {
        return Arrays.stream(text.split("\n"))
                .filter(s -> !s.contains("Корпус"))
                .filter(s -> !s.contains("группа"))
             //   .filter(s -> !s.contains(""))
                .filter(s -> !s.contains("Whatsapp"))
                .filter(s -> !s.contains("https:"))
                .filter(s -> !s.contains("———"))
                .filter(s -> !s.contains("-----"))
                .filter(s -> !s.contains("NEW COLLECTION"))
                .filter(s -> !s.contains("Распродажа"))
                .collect(Collectors.joining("\n"));
    }


}
