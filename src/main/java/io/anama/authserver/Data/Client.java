package io.anama.authserver.Data;

import java.util.List;

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
                        getAuthenticationMethods().stream().map(AuthenticationMethod::toEntity).toList());
        clientEntity
                .setGrantTypes(
                        getGrantTypes().stream().map(GrantType::toEntity).toList());

        clientEntity
                .setScopes(
                        getScopes().stream().map(Scope::toEntity).toList());
        clientEntity
                .setRedirectUrls(
                        getRedirectUrls().stream().map(url -> {
                            var rr = new io.anama.authserver.Entity.RedirectUrl();
                            rr.setUrl(url);
                            return rr;
                        }).toList());

        return clientEntity;
    }
}
