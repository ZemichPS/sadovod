package by.zemich.vkservice.infrastructure.config;

import by.zemich.vkservice.infrastructure.config.properties.VkApiProperties;
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
public class VkApiConfig {
    private final VkApiProperties vkProperties;
    public VkApiConfig(VkApiProperties vkApiProperties) {
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


}
