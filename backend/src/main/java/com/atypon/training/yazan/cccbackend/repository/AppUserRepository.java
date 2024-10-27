package com.atypon.training.yazan.cccbackend.repository;

import com.atypon.training.yazan.cccbackend.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, String> {
    Optional<AppUser> findByUsername(String username);
}
