//package by.zemich.testservice.config;
//
//import feign.RequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
//
//@Configuration
//public class FeignClientConfiguration {
//
//    @Bean
//    public RequestInterceptor requestInterceptor(DefaultOAuth2AuthorizedClientManager authorizedClientManager) {
//        return requestTemplate -> {
//            String accessToken = authorizedClientManager
//                    .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("keycloakForTest").principal("test-client").build())
//                    .getAccessToken().getTokenValue();
//            requestTemplate.header("Authorization", "Bearer " + accessToken);
//        };
//    }
//
//    @Bean
//    public DefaultOAuth2AuthorizedClientManager authorizedClientManager(
//            ClientRegistrationRepository clientRegistrationRepository,
//            OAuth2AuthorizedClientRepository authorizedClientRepository) {
//
//        DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
//                clientRegistrationRepository, authorizedClientRepository);
//
//        return authorizedClientManager;
//    }
//
//}
