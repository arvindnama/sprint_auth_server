package io.anama.authserver.entities;

import java.util.List;
import java.time.Duration;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<GrantType> grantTypes;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<RedirectUrl> redirectUrls;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Scope> scopes;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<AuthenticationMethod> authenticationMethods;

    @OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
    private ClientTokenSetting clientTokenSettings;

    public static Client from(RegisteredClient registeredClient) {
        Client client = new Client();

        client.setClientId(registeredClient.getClientId());
        client.setClientSecret(registeredClient.getClientSecret());
        client.setAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods()
                        .stream().map(c -> AuthenticationMethod.from(c, client))
                        .toList());
        client.setGrantTypes(
                registeredClient.getAuthorizationGrantTypes()
                        .stream().map(g -> GrantType.from(g, client))
                        .toList());
        client.setScopes(
                registeredClient.getScopes()
                        .stream().map(s -> Scope.from(s, client))
                        .toList());

        client.setRedirectUrls(
                registeredClient.getRedirectUris()
                        .stream().map(s -> RedirectUrl.from(s, client))
                        .toList());

        return client;
    }

    public static RegisteredClient fromClient(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientAuthenticationMethods(clientAuthenticationMethods(client.getAuthenticationMethods()))
                .authorizationGrantTypes(authorizationGrantTypes(client.getGrantTypes()))
                .scopes(scopes(client.getScopes()))
                .redirectUris(redirectUris(client.getRedirectUrls()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(client.clientTokenSettings.getAccessTokenTTL()))
                        .accessTokenFormat(new OAuth2TokenFormat(client.clientTokenSettings.getType()))
                        .build())
                .build();
    }

    private static Consumer<Set<AuthorizationGrantType>> authorizationGrantTypes(List<GrantType> grantTypes) {
        return s -> {
            for (GrantType g : grantTypes) {
                s.add(new AuthorizationGrantType(g.getGrantType()));
            }
        };
    }

    private static Consumer<Set<ClientAuthenticationMethod>> clientAuthenticationMethods(
            List<AuthenticationMethod> authenticationMethods) {
        return m -> {
            for (AuthenticationMethod a : authenticationMethods) {
                m.add(new ClientAuthenticationMethod(a.getAuthentication()));
            }
        };
    }

    private static Consumer<Set<String>> scopes(List<Scope> scopes) {
        return s -> {
            for (Scope x : scopes) {
                s.add(x.getScope());
            }
        };
    }

    private static Consumer<Set<String>> redirectUris(List<RedirectUrl> uris) {
        return r -> {
            for (RedirectUrl u : uris) {
                r.add(u.getUrl());
            }
        };
    }
}
