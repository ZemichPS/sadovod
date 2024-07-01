package by.zemich.testservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class OAuth2ClientConfig{

        @Bean
        public ClientRegistrationRepository clientRegistrationRepository() {
            ClientRegistration registration = CustomClientRegistrationBuilder()
                    .clientId("your-client-id")
                    .clientSecret("your-client-secret")
                    .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                    .scope("openid", "profile", "email")
                    .authorizationUri("https://my-oidc-provider.com/oauth2/authorize")
                    .tokenUri("https://my-oidc-provider.com/oauth2/token")
                    .userInfoUri("https://my-oidc-provider.com/userinfo")
                    .userNameAttributeName("id")
                    .jwkSetUri("https://my-oidc-provider.com/.well-known/jwks.json")
                    .build();

            return new InMemoryClientRegistrationRepository(registration);
        }

        private ClientRegistration.Builder CustomClientRegistrationBuilder() {
            return ClientRegistration.withRegistrationId("my-oidc-client");
        }
}
