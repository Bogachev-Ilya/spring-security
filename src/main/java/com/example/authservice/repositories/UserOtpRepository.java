package com.example.authservice.repositories;

import com.example.authservice.entities.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, String> {

    Optional<UserOtp> findUserOtpByUsername(String username);
}
