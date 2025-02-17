package com.uni.project.controller;

import com.uni.project.dto.AuthenticationResponse;
import com.uni.project.dto.UserLoginDTO;
import com.uni.project.dto.UserRegistrationDTO;
import com.uni.project.entity.User;
import com.uni.project.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String register(@RequestBody UserRegistrationDTO registerDto, HttpServletResponse response) {
        User userToRegister = User.builder()
                .accountName(registerDto.getAccountName())
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .build();
        authenticationService.register(userToRegister, response);
        return "Registered Successfully";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody UserLoginDTO loginDto, HttpServletResponse response) {
        User userToLogin = User.builder()
                .password(loginDto.getPassword())
                .email(loginDto.getEmail())
                .build();
        authenticationService.authenticate(userToLogin, response);
        return null;
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse logout(HttpServletResponse response, @AuthenticationPrincipal User userEntity) {
        return authenticationService.logout(userEntity, response);
    }

    @GetMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public String verify() {
        return "Authenticated :)";
    }
}
