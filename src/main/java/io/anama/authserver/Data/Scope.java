package io.anama.authserver.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.anama.authserver.Entity.Client;

public enum Scope {
    @JsonProperty("openid")
    OPEN_ID("openid"),

    @JsonProperty("profile")
    PROFILE("profile"),
    ;

    private final String scope;

    Scope(final String val) {
        this.scope = val;
    }

    @Override
    public String toString() {
        return scope;
    }

    public static io.anama.authserver.Entity.Scope toEntity(
            Scope scope,
            Client clientEntity) {
        var scopeEntity = new io.anama.authserver.Entity.Scope();
        scopeEntity.setScope(scope.toString());
        scopeEntity.setClient(clientEntity);
        return scopeEntity;

    }
}
