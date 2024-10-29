package com.atypon.training.yazan.backend.sec;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        AppUser currentUser = user.get();
        return User.builder()
                .username(currentUser.getUsername())
                .password(currentUser.getPassword())
                .roles(currentUser.getRole())
                .build();
    }
}
