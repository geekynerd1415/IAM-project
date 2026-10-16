
package com.iamlab.platform.service;

import com.iamlab.platform.repository.AuthenticationUserRepository;
import com.iamlab.platform.repository.AuthenticationUserRepository.AuthenticationUser;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final AuthenticationUserRepository authenticationUserRepository;

    public DatabaseUserDetailsService(
            AuthenticationUserRepository authenticationUserRepository) {
        this.authenticationUserRepository = authenticationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        AuthenticationUser authenticationUser =
            authenticationUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                    "Invalid email or password"
                ));

        User.UserBuilder userBuilder = User.withUsername(
            authenticationUser.email()
        )
            .password(authenticationUser.passwordHash())
            .authorities("ROLE_USER");

        if (!authenticationUser.enabled()) {
            userBuilder.disabled(true);
        }

        return userBuilder.build();
    }
}
