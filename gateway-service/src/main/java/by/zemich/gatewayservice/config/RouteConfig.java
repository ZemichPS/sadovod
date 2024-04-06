package by.zemich.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("supplier-service", route -> route
                        .path("/api/suppliers/all")
                     //   .filters(GatewayFilterSpec::tokenRelay)
                        .uri("lb://SUPPLIER-SERVICE"))

                .build();

    }


}
