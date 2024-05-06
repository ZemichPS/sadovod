package by.zemich.textprocessormicroservice.infrastracture.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mistral")
public class MistralProperties {
    private String apiKey;

    public MistralProperties(String apiKey) {
        this.apiKey = apiKey;
    }

    public MistralProperties() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
