package com.atypon.training.yazan.backend;

import com.atypon.training.yazan.backend.sec.AppUser;
import com.atypon.training.yazan.backend.sec.AppUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BackendApplication(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        appUserRepository.save(new AppUser("user", passwordEncoder.encode("123"), "USER"));
        appUserRepository.save(new AppUser("admin", passwordEncoder.encode("234"), "ADMIN"));
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
