package io.anama.authserver.Data;

import io.anama.authserver.Entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientTokenSetting {

    private int accessTokenTTL;

    private TokenType type;

    public io.anama.authserver.Entity.ClientTokenSetting toEntity(Client clientEntity) {
        var ct = new io.anama.authserver.Entity.ClientTokenSetting();
        ct.setAccessTokenTTL(getAccessTokenTTL());
        ct.setType(getType().toString());
        ct.setClient(clientEntity);
        return ct;

    }

}
