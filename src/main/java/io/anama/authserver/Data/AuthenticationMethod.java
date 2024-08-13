package io.anama.authserver.Data;

public enum AuthenticationMethod {
    CLIENT_SECRET_BASIC("client_secret_basic"),
    CLIENT_SECRET_POST("client_secret_pos t"),
    CLIENT_SECRET_JWT("client_secret_jwt"),
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
            AuthenticationMethod method) {
        var methodEntity = new io.anama.authserver.Entity.AuthenticationMethod();
        methodEntity.setAuthentication(method.toString());
        return methodEntity;

    }
}
