package io.anama.authserver.entities;

import java.util.List;
import java.time.Duration;

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
                .clientAuthenticationMethods(
                        AuthenticationMethod.clientAuthenticationMethods(client.getAuthenticationMethods()))
                .authorizationGrantTypes(GrantType.authorizationGrantTypes(client.getGrantTypes()))
                .scopes(Scope.scopes(client.getScopes()))
                .redirectUris(RedirectUrl.redirectUris(client.getRedirectUrls()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(client.clientTokenSettings.getAccessTokenTTL()))
                        .accessTokenFormat(new OAuth2TokenFormat(client.clientTokenSettings.getType()))
                        .build())
                .build();
    }

}
