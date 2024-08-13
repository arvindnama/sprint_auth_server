package io.anama.authserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.anama.authserver.Repositories.UserRepository;
import io.anama.authserver.model.SecurityUser;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username);

        return user.map(SecurityUser::new).orElseThrow(
                () -> new UsernameNotFoundException("Unable to find user"));
    }

}
