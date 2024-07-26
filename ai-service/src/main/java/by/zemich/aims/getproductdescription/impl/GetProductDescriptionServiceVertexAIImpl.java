package by.zemich.aims.getproductdescription.impl;

import by.zemich.aims.getproductdescription.GetProductDescriptionService;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GetProductDescriptionServiceVertexAIImpl implements GetProductDescriptionService {
    private final String projectId = "sadovod-423711";
    private final String location = "us-central1";
   // private final String modelName = "gemini-1.0-pro-vision-001";
    private final String modelName = "gemini-1.5-pro-001";


    @Override
    public String getProductDescription(String request) throws IOException {


        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(request);

            GenerationConfig generationConfig = GenerationConfig.newBuilder()
                    .setTemperature(0.1f)
                    .build();
            model.setGenerationConfig(generationConfig);

            return ResponseHandler.getText(response);
        }
    }
}
