package by.zemich.gatewayservice.config.security;

import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

public class OAuthClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    private final String registrationId;

    @Setter
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public OAuthClientHttpRequestInterceptor(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager, String registrationId) {
        this.oAuth2AuthorizedClientManager = oAuth2AuthorizedClientManager;
        this.registrationId = registrationId;

    }


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
            OAuth2AuthorizedClient authorizedClient = this.oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId(this.registrationId)
                    .principal(securityContextHolderStrategy.getContext().getAuthentication())
                    .build()
            );


            assert authorizedClient != null;
            request.getHeaders().setBearerAuth(authorizedClient.getAccessToken().getTokenValue());
        }

        return execution.execute(request, body);
    }
}
