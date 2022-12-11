package com.example.authservice.repositories;

import com.example.authservice.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {

    Optional<Otp> findOtpByUsername(String username);
}
