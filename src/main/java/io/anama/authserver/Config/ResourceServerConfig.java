package io.anama.authserver.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationFilterChain(HttpSecurity http) throws Exception {

        /**
         * Configure to validate the signature of jwt.
         */
        http.oauth2ResourceServer(
                c -> c.jwt(
                        j -> j.jwkSetUri("http://localhost:8080/oauth2/jwks")));

        /**
         * Configure to use introspect api to validate token ..
         * unlike jwt where the verification is done in house (only signature
         * verification)
         */

        // http.oauth2ResourceServer(
        // c -> c.opaqueToken(
        // o -> o.introspectionUri("http://localhost:8080/oauth2/introspect")
        // .introspectionClientCredentials("client1", "12345")));

        /**
         * Define routes / requests that need to be authorized
         * We can whitelist if needed.
         *
         * Default is to authenticate all requests.
         */
        http.securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
