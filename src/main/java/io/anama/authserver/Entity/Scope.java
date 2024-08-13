package io.anama.authserver.Entity;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scopes")
@Getter
@Setter
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String scope;

    @ManyToOne
    private Client client;

    public static Scope from(String scope, Client client) {
        Scope redirectUrl = new Scope();

        redirectUrl.setScope(scope);
        redirectUrl.setClient(client);
        return redirectUrl;
    }

    public static Consumer<Set<String>> scopes(List<Scope> scopes) {
        return s -> {
            for (Scope x : scopes) {
                s.add(x.getScope());
            }
        };
    }
}
