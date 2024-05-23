package by.zemich.aims.getproductdescription;

import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class GetProductDescriptionVertexAiService implements GetProductDescriptionServiceApi {

    private final GenerativeModel generativeModel;

    public GetProductDescriptionVertexAiService(GenerativeModel generativeModel) {
        this.generativeModel = generativeModel;
    }

    @Override
    public GetProductDescriptionResponse createJsonProductDescription(GetProductDescriptionRequest request) {
        try {
            final GenerateContentResponse response = getGenerateContentResponse(request);
            String result = ResponseHandler.getText(response);
            result = result.replace("```json", "");

            log.info(result);
            return new GetProductDescriptionResponse(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private GenerateContentResponse getGenerateContentResponse(GetProductDescriptionRequest request) throws IOException {
        String textPrompt = """
                 You are a bot that converts product information from post to JSON.
                 JSON values to russian language.
                 JSON template: %s. Text source: %s
                """.formatted(request.getJsonDestination(), request.getSource());

        final GenerationConfig generationConfig = GenerationConfig.newBuilder()
                .setTemperature(0.4F)
                .build();

        return generativeModel.generateContent(textPrompt, generationConfig);
    }


//    private GenerateContentResponse getGenerateContentResponse(GetProductDescriptionRequest request) throws IOException {
//        final Content modelContent = Content.newBuilder()
//                .setRole("MODEL")
//                .setParts(0, Part.newBuilder()
//                        .setText("""
//                                You are a bot that converts product information from post to JSON. JSON values to russian.
//                                JSON template: %s
//                                """.formatted(request.getJsonDestination()))
//                        .build())
//                .build();
//
//        final Content userContent = Content.newBuilder()
//                .setRole("USER")
//                .setParts(0, Part.newBuilder()
//                        .setText("""
//                                source: %s
//                                """.formatted(request.getSource()))
//                        .build())
//                .build();
//
//        List<Content> contentList = List.of(userContent, modelContent);
//
//        final GenerationConfig generationConfig = GenerationConfig.newBuilder()
//                .setTemperature(0.4F)
//                .build();
//
//        return generativeModel.generateContent(contentList, generationConfig);
//    }


}
