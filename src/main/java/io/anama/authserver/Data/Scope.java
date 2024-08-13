package io.anama.authserver.Data;

public enum Scope {
    OPEN_ID("openId"),
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
            Scope scope) {
        var scopeEntity = new io.anama.authserver.Entity.Scope();
        scopeEntity.setScope(scope.toString());
        return scopeEntity;

    }
}
