package by.zemich.gatewayservice.config;

import by.zemich.gatewayservice.config.security.filters.OAuth2TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class RouteConfig {

    private final OAuth2TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory;

    public RouteConfig(OAuth2TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory) {
        this.tokenRelayGatewayFilterFactory = tokenRelayGatewayFilterFactory;
    }

    @Bean
    RouteLocator gateway(RouteLocatorBuilder locatorBuilder) {

        return locatorBuilder.routes()
                .route("suppliers", predicateSpec -> predicateSpec.path("/api/v1/suppliers/**")
                        .filters(filter -> filter.filters(tokenRelayGatewayFilterFactory.apply(vkMsConfig())))
                        .uri("lb://SUPPLIER-SERVICE"))
                .build();
    }

    private OAuth2TokenRelayGatewayFilterFactory.Config vkMsConfig() {
        OAuth2TokenRelayGatewayFilterFactory.Config config = new OAuth2TokenRelayGatewayFilterFactory.Config();
        config.setClientRegistrationId("vkMicroserviceClient");
        return config;
    }


}
