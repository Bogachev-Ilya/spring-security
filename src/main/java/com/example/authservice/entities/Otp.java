package com.example.authservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Otp {

    @Id
    private Long otpId;
    private String username;
    private String code;
}
