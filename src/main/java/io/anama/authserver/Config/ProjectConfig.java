package io.anama.authserver.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

@Configuration
public class ProjectConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain asFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        /*
         * In case a user / client requests Apis without authentication
         * Redirect them to login page via exceptionHandling
         */
        http.exceptionHandling(
                c -> c.defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint("/login"),
                        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)));

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {

        /**
         * Adding a login page.
         * It is possible to customize
         */
        http.formLogin(Customizer.withDefaults());
        /**
         * Define routes / requests that need to be authorized
         * We can whitelist if needed.
         *
         * Default is to authenticate all requests.
         */
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationSeverSetting() {
        /**
         * Authorization Server Setting
         * Allows customizing Authorization end point Urls
         */
        return AuthorizationServerSettings.builder().build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * For Encoding Secrets.
         * TODO: replace with Bcrypt
         */
        return NoOpPasswordEncoder.getInstance();
    }
}
