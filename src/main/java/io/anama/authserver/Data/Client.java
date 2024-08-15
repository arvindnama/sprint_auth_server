package io.anama.authserver.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.anama.authserver.Entity.ClientTokenSetting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    private String clientId;

    private String clientSecret;

    private List<GrantType> grantTypes;

    private List<String> redirectUrls;

    private List<Scope> scopes;

    private List<AuthenticationMethod> authenticationMethods;

    private ClientTokenSetting clientTokenSettings;

    public io.anama.authserver.Entity.Client toClientEntity() {
        var clientEntity = new io.anama.authserver.Entity.Client();
        clientEntity.setClientId(getClientId());
        /**
         * TODO: use bycript to hash the password
         */
        clientEntity.setClientSecret(getClientSecret());
        clientEntity
                .setAuthenticationMethods(
                        getAuthenticationMethods()
                                .stream()
                                .map(m -> AuthenticationMethod.toEntity(m, clientEntity))
                                .toList());
        clientEntity
                .setGrantTypes(
                        getGrantTypes()
                                .stream()
                                .map(g -> GrantType.toEntity(g, clientEntity))
                                .toList());

        clientEntity
                .setScopes(
                        getScopes().stream().map(s -> Scope.toEntity(s, clientEntity)).toList());
        clientEntity
                .setRedirectUrls(
                        getRedirectUrls().stream().map(url -> {
                            var rr = new io.anama.authserver.Entity.RedirectUrl();
                            rr.setUrl(url);
                            rr.setClient(clientEntity);
                            return rr;
                        }).toList());

        return clientEntity;
    }
}
