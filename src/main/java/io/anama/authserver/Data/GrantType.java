package io.anama.authserver.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GrantType {
    @JsonProperty("authorization_code")
    AUTHORIZATION_CODE("authorization_code"),

    @JsonProperty("refresh_token")
    REFRESH_TOKEN("refresh_token"),

    @JsonProperty("client_credentials")
    CLIENT_CREDENTIALS("client_credentials"),;

    private final String grantType;

    GrantType(final String val) {
        this.grantType = val;
    }

    @Override
    public String toString() {
        return grantType;

    }

    public static io.anama.authserver.Entity.GrantType toEntity(
            GrantType type,
            io.anama.authserver.Entity.Client clientEntity) {
        var grantTypeEntity = new io.anama.authserver.Entity.GrantType();
        grantTypeEntity.setGrantType(type.toString());
        grantTypeEntity.setClient(clientEntity);
        return grantTypeEntity;

    }
}
