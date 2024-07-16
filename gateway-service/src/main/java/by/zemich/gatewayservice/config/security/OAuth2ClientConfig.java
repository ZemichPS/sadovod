package by.zemich.gatewayservice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class OAuth2ClientConfig {

    private final String VK_MICROSERVICE_CLIENT_NAME = "vk-microservice-client";
    private final String VK_MICROSERVICE_CLIENT_REGISTRATION_ID = "vkMicroserviceClient";
    private final String VK_MICROSERVICE_CLIENT_ID = "vk-msclient";
    private final String VK_MICROSERVICE_CLIENT_SECRET = "Y6EAYbPwJGVlIrDL83qm8dE0B9F9KI0F";

    private final String USER_CLIENT_NAME = "ai-msclient";
    private final String USER_CLIENT_CLIENT_REGISTRATION_ID = "keycloakService";
    private final String USER_CLIENT_CLIENT_ID = "identity-provider-client";
    private final String USER_CLIENT_CLIENT_SECRET = "";

    @Bean
    public InMemoryReactiveClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryReactiveClientRegistrationRepository(this.vkmicroservicekeycloakClientRegistration());
    }

    private ClientRegistration vkmicroservicekeycloakClientRegistration() {
        return ClientRegistration.withRegistrationId(VK_MICROSERVICE_CLIENT_REGISTRATION_ID)
                .clientId(VK_MICROSERVICE_CLIENT_ID)
                .clientSecret(VK_MICROSERVICE_CLIENT_SECRET)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/token")
                .jwkSetUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/certs")
                .clientName(VK_MICROSERVICE_CLIENT_NAME)
                .build();
    }

    private ClientRegistration cataloguemicroservicekeycloakClientRegistration() {
        return ClientRegistration.withRegistrationId(VK_MICROSERVICE_CLIENT_REGISTRATION_ID)
                .clientId(VK_MICROSERVICE_CLIENT_ID)
                .clientSecret(VK_MICROSERVICE_CLIENT_SECRET)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/token")
                .jwkSetUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/certs")
                .clientName(VK_MICROSERVICE_CLIENT_NAME)
                .build();
    }




//     //КЛИЕНТ ДЛЯ ПОЛЬЗОВАТЕЛЕЙ
//    private ClientRegistration keycloakClientRegistration() {
//        return ClientRegistration.withRegistrationId("keycloakForTest")
//                .clientId("test-client")
//                .clientSecret("MmUjv4n9oufdAnXYv1rk8OwpU3vyv6vN")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .redirectUri("http://localhost:7452/login/oauth2/code/keycloakForTest")
//                .scope("get_suppliers")
//                .authorizationUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/auth")
//                .tokenUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/token")
//                .userInfoUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/userinfo")
//                .userNameAttributeName("preferred_username")
//                .jwkSetUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/certs")
//                .clientName("test-client-name")
//                .build();
//    }


}
