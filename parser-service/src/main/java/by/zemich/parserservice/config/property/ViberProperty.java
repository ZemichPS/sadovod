package by.zemich.parserservice.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "viber")
public class ViberProperty {
    private String token;
    private String id;

    public ViberProperty(String token, String userId) {
        this.token = token;
        this.id = userId;
    }

    public ViberProperty() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
