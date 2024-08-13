package io.anama.authserver.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.anama.authserver.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("""
            SELECT c From Client c WHERE c.clientId = :clientId
            """)
    Optional<Client> findByClientId(String clientId);

}
