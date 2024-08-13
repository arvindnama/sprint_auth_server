package io.anama.authserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import io.anama.authserver.Repositories.ClientRepository;
import io.anama.authserver.entities.Client;

@Service
@Transactional(readOnly = true)
public class ClientService implements RegisteredClientRepository {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Nullable
    public RegisteredClient findByClientId(String clientId) {
        var client = clientRepository.findByClientId(clientId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find client"));
        return Client.fromClient(client);
    }

    @Override
    @Nullable
    public RegisteredClient findById(String id) {
        var client = clientRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find client"));

        return Client.fromClient(client);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(Client.from(registeredClient));
    }

}
