package by.zemich.parserservice.config;

import by.zemich.parserservice.config.property.VkApiProperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CommonConfig {
    private final VkApiProperties vkProperties;
    public CommonConfig(VkApiProperties vkApiProperties) {
        this.vkProperties = vkApiProperties;
    }

    @Bean
    TransportClient transportClient() {
        return new HttpTransportClient();
    }

    @Bean()
    VkApiClient vkApiClient(TransportClient transportClient) {
        return new VkApiClient(transportClient);
    }

    @Bean
    UserAuthResponse userAuthResponse(VkApiClient vkApiClient) throws ClientException, ApiException {
        return vkApiClient.oAuth()
                .userAuthorizationCodeFlow(vkProperties.getAppId(),
                        vkProperties.getClientSecret(),
                        vkProperties.getRedirectURI(),
                        vkProperties.getCode())
                .execute();
    }

    @Bean
    UserActor userActor(UserAuthResponse userAuthResponse){
        return new UserActor(userAuthResponse.getUserId(), userAuthResponse.getAccessToken());
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }



}
