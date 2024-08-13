package io.anama.authserver.Data;

public enum GrantType {
    AUTHORIZATION_CODE("authorization_code"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials"),
    ;

    private final String grantType;

    GrantType(final String val) {
        this.grantType = val;
    }

    @Override
    public String toString() {
        return grantType;

    }

    public static io.anama.authserver.Entity.GrantType toEntity(
            GrantType type) {
        var grantTypeEntity = new io.anama.authserver.Entity.GrantType();
        grantTypeEntity.setGrantType(type.toString());
        return grantTypeEntity;

    }
}
