package by.zemich.gatewayservice.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(2)
public class OAuth2TokenRelayFilter implements GatewayFilter {
    private static final Logger log = LoggerFactory.getLogger(OAuth2TokenRelayFilter.class);
    private final ReactiveOAuth2AuthorizedClientManager authorizedClientManager;

    public OAuth2TokenRelayFilter(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ClientRegistration clientRegistration = exchange.getAttribute("clientRegistrationId");
        assert clientRegistration != null;
        String clientRegistrationId = clientRegistration.getRegistrationId();
      
        return authorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationId)
                        .principal(String.valueOf(exchange.getPrincipal()))
                        .build())
                .flatMap(authorizedClient -> {
                    exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION, "Bearer " + authorizedClient.getAccessToken().getTokenValue());
                    return chain.filter(exchange);
                });


    }

    ;
}
