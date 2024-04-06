package by.zemich.userservice.config;


import by.zemich.userservice.service.api.UserWebService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
@Log4j2
public class RoutingConfiguration {

    private final UserWebService userService;

    public RoutingConfiguration(UserWebService userService) {
        this.userService = userService;
    }

    @Bean
    public RouterFunction<ServerResponse> rote() {

        return RouterFunctions.route().path("/api/users", builder -> builder
                .nest(accept(APPLICATION_JSON), builder2 -> builder2
                        .GET("/all", userService::getAll)
                        .GET("/get_by_email", userService::getByEmailAddress)
                        .POST("/new", userService::save)
                        .POST("/update", userService::update)
                )).build();
    }


}
