package com.atypon.training.yazan.cccbackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AppUser {
    @Id
    private String Id;
    private String username;
    private String password;
    private String role;
}
