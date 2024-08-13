package io.anama.authserver.Data;

public enum TokenType {
    SELF_CONTAINED("self-contained"),
    REFERENCE("reference"),
    ;

    private final String tokenType;

    TokenType(final String val) {
        this.tokenType = val;
    }

    @Override
    public String toString() {
        return tokenType;

    }
}
