package io.anama.authserver.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.anama.authserver.Data.Client;
import io.anama.authserver.Repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(":create")
    public Integer createClient(@RequestBody Client client) {
        var entity = client.toClientEntity();
        clientRepository.save(entity);

        return clientRepository.findByClientId(client.getClientId()).orElseThrow().getId();
    }

}
