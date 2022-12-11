package com.example.authservice.controllers;

import com.example.authservice.entities.Otp;
import com.example.authservice.entities.UserOtp;
import com.example.authservice.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/add")
    public UserOtp addUser(@RequestBody UserOtp userOtp) {
        return userService.addUser(userOtp);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody UserOtp userOtp) {
        userService.auth(userOtp);
    }

    @PostMapping("/otp/check")
    public ResponseEntity check(@RequestBody Otp otp) {
        if (userService.check(otp)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
