package io.anama.authserver.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.anama.authserver.Data.Client;
import io.anama.authserver.Repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/api/clients:create")
    public Integer createClient(@RequestBody Client client) {

        var clientInDB = clientRepository.findByClientId(client.getClientId());
        if (!clientInDB.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "Client Already Exists");
        }

        var entity = client.toClientEntity();
        clientRepository.save(entity);

        return clientRepository.findByClientId(client.getClientId()).orElseThrow().getId();
    }

}
