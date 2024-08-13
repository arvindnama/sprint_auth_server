package io.anama.authserver.entities;

import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authentication_methods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String authentication;

    @ManyToOne
    private Client client;

    public static AuthenticationMethod from(
            ClientAuthenticationMethod clientAuthMethod,
            Client client

    ) {
        AuthenticationMethod method = new AuthenticationMethod();
        method.setAuthentication(clientAuthMethod.getValue());
        method.setClient(client);
        return method;
    }
}
