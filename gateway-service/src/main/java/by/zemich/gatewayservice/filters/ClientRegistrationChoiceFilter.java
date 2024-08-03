package by.zemich.gatewayservice.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class ClientRegistrationChoiceFilter implements GatewayFilter {
    private static final Logger log = LoggerFactory.getLogger(ClientRegistrationChoiceFilter.class);
    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    public ClientRegistrationChoiceFilter(ReactiveClientRegistrationRepository reactiveClientRegistrationRepository) {
        this.clientRegistrationRepository = reactiveClientRegistrationRepository;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        String registrationId = getRegistrationIdForPath(path);
        return clientRegistrationRepository.findByRegistrationId(registrationId)
                .flatMap(clientRegistration -> {
                    exchange.getAttributes().put("clientRegistrationId", clientRegistration);
                    return chain.filter(exchange);
                });

    }

    String getRegistrationIdForPath(String path){
        return switch (path.split("/")[3]){
            case "suppliers" -> "vkMicroserviceClient";
            case "ai" -> "aiMicroserviceClient";
            default -> throw new IllegalStateException("Unexpected value: " + path.split("/")[3]);
        };
    }
}
