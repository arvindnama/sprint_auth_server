package io.anama.authserver.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TokenType {
    @JsonProperty("self-contained")
    SELF_CONTAINED("self-contained"),

    @JsonProperty("reference")
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
