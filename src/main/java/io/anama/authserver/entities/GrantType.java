package io.anama.authserver.entities;

import org.springframework.security.oauth2.core.AuthorizationGrantType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "grant_types")
@Getter
@Setter
public class GrantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "grant_type")
    private String grantType;

    @ManyToOne
    private Client client;

    public static GrantType from(AuthorizationGrantType type, Client client) {
        GrantType grant = new GrantType();

        grant.setGrantType(type.getValue());
        grant.setClient(client);
        return grant;
    }
}
