package io.anama.authserver.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.anama.authserver.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
            SELECT u FROM User u WHERE u.userName= :userName
            """)
    Optional<User> findByUserName(String userName);

}
