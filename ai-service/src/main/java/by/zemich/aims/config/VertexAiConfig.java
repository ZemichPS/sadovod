package by.zemich.aims.config;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertexAiConfig {
    private final String projectId = "sadovod-423711";
    private final String location = "us-central1";
    private final String modelName = "gemini-1.0-pro";

    @Bean
    public VertexAI vertexAI() {
        return new VertexAI(projectId, location);
    }

    @Bean
    public GenerativeModel generativeModel(VertexAI vertexAI) {
        return GenerativeModel.newBuilder()
                .setVertexAi(vertexAI)
                .setModelName(modelName)
                .build();
    }
}
