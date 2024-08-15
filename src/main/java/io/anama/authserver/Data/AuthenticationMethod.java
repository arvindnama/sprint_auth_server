package io.anama.authserver.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.anama.authserver.Entity.Client;

public enum AuthenticationMethod {
    @JsonProperty("client_secret_basic")
    CLIENT_SECRET_BASIC("client_secret_basic"),

    @JsonProperty("client_secret_post")
    CLIENT_SECRET_POST("client_secret_post"),

    @JsonProperty("client_secret_jwt")
    CLIENT_SECRET_JWT("client_secret_jwt"),

    @JsonProperty("private_key_jwt")
    PRIVATE_KEY_JWT("private_key_jwt"),;

    private final String authMethod;

    AuthenticationMethod(final String val) {
        this.authMethod = val;
    }

    @Override
    public String toString() {
        return authMethod;

    }

    public static io.anama.authserver.Entity.AuthenticationMethod toEntity(
            AuthenticationMethod method,
            Client clientEntity) {
        var methodEntity = new io.anama.authserver.Entity.AuthenticationMethod();
        methodEntity.setAuthentication(method.toString());
        methodEntity.setClient(clientEntity);
        return methodEntity;

    }
}
