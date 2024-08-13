package io.anama.authserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import io.anama.authserver.Repositories.ClientRepository;
import io.anama.authserver.entities.Client;

@Service
public class ClientService implements RegisteredClientRepository {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Nullable
    public RegisteredClient findByClientId(String clientId) {
        var client = clientRepository.findByClientId(clientId).orElseThrow(
                () -> new RuntimeException("error"));
        return Client.fromClient(client);
    }

    @Override
    @Nullable
    public RegisteredClient findById(String id) {
        var client = clientRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> new RuntimeException("error"));

        return Client.fromClient(client);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(Client.from(registeredClient));
    }

}
