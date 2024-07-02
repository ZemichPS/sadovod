//package by.zemich.testservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class OAuth2ClientConfig {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.sessionManagement(sessionManagement ->
//                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
//        return http.build();
//    }
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(this.keycloakClientRegistration());
//    }
//
//    private ClientRegistration keycloakClientRegistration() {
//        return ClientRegistration.withRegistrationId("keycloakForTest")
//                .clientId("test-client")
//                .clientSecret("MmUjv4n9oufdAnXYv1rk8OwpU3vyv6vN")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .tokenUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/token")
//                .jwkSetUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/certs")
//                .clientName("test-client-name")
//                .build();
//    }
//        // КЛИЕНТ ДЛЯ ПОЛЬЗОВАТЕЛЕЙ
////    private ClientRegistration keycloakClientRegistration() {
////        return ClientRegistration.withRegistrationId("keycloakForTest")
////                .clientId("test-client")
////                .clientSecret("MmUjv4n9oufdAnXYv1rk8OwpU3vyv6vN")
////                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
////                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
////                .redirectUri("http://localhost:7452/login/oauth2/code/keycloakForTest")
////                .scope("get_suppliers")
////                .authorizationUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/auth")
////                .tokenUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/token")
////                .userInfoUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/userinfo")
////                .userNameAttributeName("preferred_username")
////                .jwkSetUri("http://localhost:8282/realms/sadovod/protocol/openid-connect/certs")
////                .clientName("test-client-name")
////                .build();
////    }
//
//
//}
