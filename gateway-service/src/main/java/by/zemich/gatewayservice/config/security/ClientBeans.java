//package by.zemich.gatewayservice.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
//import org.springframework.web.client.RestClient;
//
//@Configuration
//public class ClientBeans {
//
//    private final ClientRegistrationRepository clientRegistrationRepository;
//
//    public ClientBeans(ClientRegistrationRepository clientRegistrationRepository) {
//        this.clientRegistrationRepository = clientRegistrationRepository;
//    }
//
//    @Bean
//    public RestClient restClient(
//                                 OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
//                                 ){
//        return RestClient.builder()
//                .baseUrl("http://localhost:8081")
//                .requestInterceptor(
//                       new OAuthClientHttpRequestInterceptor(
//                               new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository), "keycloak"
//                       )
//                )
//                .build();
//    }
//
//
//
//
//}
