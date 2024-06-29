package by.zemich.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteConfig {
    @Bean
    RouteLocator gateway(RouteLocatorBuilder locatorBuilder) {

        return locatorBuilder.routes()
                .route("suppliers", predicateSpec-> predicateSpec.path("api/v1/suppliers/**").uri("lb://SUPPLIER-SERVICE"))
                .build();
    }

}
