package by.zemich.aims.config;

import by.zemich.aims.config.properties.MistralProperties;
import nl.dannyj.mistral.MistralClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MistralConfig {

    private final MistralProperties mistralProperties;

    public MistralConfig(MistralProperties mistralProperties) {
        this.mistralProperties = mistralProperties;
    }

    @Bean
    MistralClient mistralClient() {
        return new MistralClient(mistralProperties.getApiKey());
    }



}
