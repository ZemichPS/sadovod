package by.zemich.vkms.infrastructure.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "vk.api")
public class VkApiProperties {
    private Integer appId;
    private String clientSecret;

    private String redirectURI;

    private String code;

    public VkApiProperties(Integer appId, String clientSecret, String redirectURI, String code) {
        this.appId = appId;
        this.clientSecret = clientSecret;
        this.redirectURI = redirectURI;
        this.code = code;
    }

    public VkApiProperties() {
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
