//package by.zemich.textprocessormicroservice.infrastracture.config;
//
//import org.springframework.ai.mistralai.MistralAiChatClient;
//import org.springframework.ai.mistralai.MistralAiChatOptions;
//import org.springframework.ai.mistralai.api.MistralAiApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MistralConfig {
//
//    @Bean
//    MistralAiApi mistralAiApi() {
//       // return new MistralAiApi(System.getenv("MISTRAL_AI_API_KEY"));
//        return new MistralAiApi("9PkC2c0r7Dh5SI3q9ZBCbKhT14dVvGt5");
//    }
//
//
//    @Bean
//    MistralAiChatClient mistralAiChatClient(MistralAiApi aiApi) {
//        MistralAiChatOptions options = MistralAiChatOptions.builder()
//                .withModel(MistralAiApi.ChatModel.TINY.getValue())
//                .withTemperature(0.4f)
//                .withMaxToken(300)
//                .build();
//        return new MistralAiChatClient(aiApi, options);
//    }
//}
