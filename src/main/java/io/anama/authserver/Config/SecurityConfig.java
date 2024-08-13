package io.anama.authserver.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // @Bean
    // @Order(1)
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http.oauth2ResourceServer(
    // c -> c.jwt(
    // j -> j.jwkSetUri("http://localhost:8080/oAuth2/jwks")));

    // /**
    // * Configure to use introspect api to validate token ..
    // * unlike jwt where the verification is done in house (only signature
    // * verification)
    // */

    // // http.oauth2ResourceServer(
    // // c -> c.opaqueToken(
    // // o -> o.introspectionUri("http://localhost:8080/oauth2/introspect")
    // // .introspectionClientCredentials("client1", "12345")));

    // http.authorizeHttpRequests(
    // c -> c.anyRequest().permitAll());
    // return http.build();
    // }
}
