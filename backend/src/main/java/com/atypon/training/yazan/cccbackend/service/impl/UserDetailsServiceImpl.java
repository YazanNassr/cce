package com.atypon.training.yazan.cccbackend.service.impl;

import com.atypon.training.yazan.cccbackend.model.AppUser;
import com.atypon.training.yazan.cccbackend.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);

        if (user.isEmpty()) {
            return null;
        }

        AppUser crrUser = user.get();
        User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(crrUser.getPassword());
        builder.roles(crrUser.getRole());
        return builder.build();
    }
}
