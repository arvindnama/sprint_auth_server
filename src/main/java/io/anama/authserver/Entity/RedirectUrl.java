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
@Table(name = "redirect_urls")
@Getter
@Setter
public class RedirectUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    @ManyToOne
    private Client client;

    public static RedirectUrl from(String url, Client client) {
        RedirectUrl redirectUrl = new RedirectUrl();

        redirectUrl.setUrl(url);
        redirectUrl.setClient(client);
        return redirectUrl;
    }

    public static Consumer<Set<String>> redirectUris(List<RedirectUrl> uris) {
        return r -> {
            for (RedirectUrl u : uris) {
                r.add(u.getUrl());
            }
        };
    }
}
