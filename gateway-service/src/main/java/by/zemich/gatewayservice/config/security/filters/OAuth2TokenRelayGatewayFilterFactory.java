package by.zemich.gatewayservice.config.security.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.stereotype.Component;

@Component
public class OAuth2TokenRelayGatewayFilterFactory extends AbstractGatewayFilterFactory<OAuth2TokenRelayGatewayFilterFactory.Config> {

    private final ReactiveOAuth2AuthorizedClientManager authorizedClientManager;

    public OAuth2TokenRelayGatewayFilterFactory(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String clientRegistrationId = config.getClientRegistrationId();
            return authorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationId)
                            .principal(String.valueOf(exchange.getPrincipal()))
                            .build())
                    .flatMap(authorizedClient -> {
                        exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION, "Bearer " + authorizedClient.getAccessToken().getTokenValue());
                        return chain.filter(exchange);
                    });
        };
    }


    public static class Config {
        private String clientRegistrationId;

        public String getClientRegistrationId() {
            return clientRegistrationId;
        }

        public void setClientRegistrationId(String clientRegistrationId) {
            this.clientRegistrationId = clientRegistrationId;
        }
    }
}
