package com.example.authservice.services;

import com.example.authservice.entities.Otp;
import com.example.authservice.entities.UserOtp;
import com.example.authservice.repositories.OtpRepository;
import com.example.authservice.repositories.UserOtpRepository;
import com.example.authservice.utils.GenerateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.random.RandomGenerator;

@Service
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserOtpRepository userOtpRepository;

    @Autowired
    private OtpRepository otpRepository;

    public UserOtp addUser(UserOtp userOtp) {
        userOtp.setPassword(passwordEncoder.encode(userOtp.getPassword()));
        userOtp.setUserId(RandomGenerator.getDefault().nextLong());
        return userOtpRepository.save(userOtp);
    }

    public void auth(UserOtp userOtp) {
        Optional<UserOtp> o =
                userOtpRepository.findUserOtpByUsername(userOtp.getUsername());

        if(o.isPresent()) {
            UserOtp u = o.get();
            if (passwordEncoder.matches(userOtp.getPassword(), u.getPassword())) {
                renewOtp(u);
            } else {
                throw new BadCredentialsException("Bad credentials.");
            }
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }

        return false;
    }

    private void renewOtp(UserOtp u) {
        String code = GenerateCodeUtil.generateCode();

        Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            otp.setCode(code);
            log.info(otp.toString());
        } else {
            Otp otp = new Otp();
            otp.setOtpId(RandomGenerator.getDefault().nextLong());
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
            log.info(otp.toString());
        }
    }

}
