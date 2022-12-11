package com.example.authservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserOtp {

    @Id
    private Long userId;
    private String username;
    private String password;
}
