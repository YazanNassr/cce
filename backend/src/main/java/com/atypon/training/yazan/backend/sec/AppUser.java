package com.atypon.training.yazan.backend.sec;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class AppUser {
    @Id
    private String username;
    private String password;
    private String role;
}
