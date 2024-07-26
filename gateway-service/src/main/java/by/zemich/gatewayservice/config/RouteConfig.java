package by.zemich.gatewayservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteConfig {

    private final GatewayFilter clientRegistrationChoiceFilter;
    private final GatewayFilter oAuth2TokenRelayFilter;
    private final GatewayFilter productCacheFilter;

    public RouteConfig(@Qualifier("clientRegistrationChoiceFilter") GatewayFilter clientRegistrationChoiceFilter,
                       @Qualifier("OAuth2TokenRelayFilter") GatewayFilter oAuth2TokenRelayFilter,
                       @Qualifier("productCacheFilter") GatewayFilter productCacheFilter) {
        this.clientRegistrationChoiceFilter = clientRegistrationChoiceFilter;
        this.oAuth2TokenRelayFilter = oAuth2TokenRelayFilter;
        this.productCacheFilter = productCacheFilter;
    }

    @Bean
    RouteLocator gateway(RouteLocatorBuilder locatorBuilder) {

        return locatorBuilder.routes()
                .route("suppliers", predicateSpec -> predicateSpec.path("/api/v1/suppliers/**")
                        .filters(f -> f.filter(clientRegistrationChoiceFilter).filter(oAuth2TokenRelayFilter))
                        .uri("lb://SUPPLIER-SERVICE"))
                .route("ai-service", predicateSpec -> predicateSpec.path("/api/v1/ai/**")
                        .filters(f -> f.filter(clientRegistrationChoiceFilter).filter(oAuth2TokenRelayFilter))
                        .uri("lb://AI-SERVICE"))
                .route("catalogue-service", predicateSpec -> predicateSpec.path("/api/v1/catalogue/**")
                        .filters(f-> f.filter(productCacheFilter))
                        .uri("lb://CATALOGUE-SERVICE")
                )
                .build();
    }
}
