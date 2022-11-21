package org.example.Service.ServiceImpl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return switch (username) {
            case "admin" -> new User("admin", "adminPassword", Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
            case "user" -> new User("user", "userPassword", Collections.singleton(new SimpleGrantedAuthority("USER")));
            default -> throw new UsernameNotFoundException("System can't find " + username + "user");
        };
    }
}
