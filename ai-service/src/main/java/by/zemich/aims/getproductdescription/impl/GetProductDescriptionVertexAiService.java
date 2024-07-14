package by.zemich.aims.getproductdescription.impl;

import by.zemich.aims.getproductdescription.GetProductDescriptionServiceApi;
import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Order(3)
@Slf4j
public class GetProductDescriptionVertexAiService implements GetProductDescriptionServiceApi {

    private final GenerativeModel generativeModel;

    public GetProductDescriptionVertexAiService(GenerativeModel generativeModel) {
        this.generativeModel = generativeModel;
    }

    @Override
    public String getProductDescription(String request) {
        try {
            GenerateContentResponse response = getGenerateContentResponse(request);
            String result = ResponseHandler.getText(response);
            log.info(result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private GenerateContentResponse getGenerateContentResponse(String request) throws IOException {

        final GenerationConfig generationConfig = GenerationConfig.newBuilder()
                .setTemperature(0.4F)
                .build();
        return generativeModel.generateContent(request, generationConfig);
    }
}
