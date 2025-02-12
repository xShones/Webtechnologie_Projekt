package com.uni.project.service;

import com.uni.project.dto.AuthenticationResponse;
import com.uni.project.entity.User;
import com.uni.project.exception.NoUsersFoundException;
import com.uni.project.security.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final static int ONE_DAY = 86400 * 1000;

    @Autowired
    public AuthenticationServiceImp(UserService userService,
                                    JwtService jwtService,
                                    AuthenticationManager authenticationManager,
                                    PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(User user, HttpServletResponse response) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        var jwtToken = jwtService.generateToken(user);
        setJwtCookie(response, jwtToken);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
    }

    @Override
    public void authenticate(User user, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        User userToFind = userService.getUserByEmail(user.getEmail());
        var jwtToken = jwtService.generateToken(userToFind);
        setJwtCookie(response, jwtToken);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
    }

    @Override
    public AuthenticationResponse logout(User user, HttpServletResponse response) {
        String expiredToken = setRevokedJwtCookie(response, user);
        return AuthenticationResponse.builder()
                .token(expiredToken)
                .build();
    }

    private void setJwtCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwt", token);

        cookie.setHttpOnly(true);       // Prevent access to cookie from JavaScript
        cookie.setSecure(true);         // Only send cookie over HTTPS
        cookie.setPath("/");            // Cookie should be available across the entire domain
        cookie.setMaxAge((int) ONE_DAY / 1000);  // Set expiry time for the cookie (same as JWT expiration)
        cookie.setAttribute("SameSite", "None");
        response.addCookie(cookie);
    }

    private String setRevokedJwtCookie(HttpServletResponse response, User user) {
//        String expiredToken = jwtService.generateExpiredToken(user);
        String expiredToken = "";
        Cookie cookie = new Cookie("jwt", expiredToken);

        cookie.setHttpOnly(true);       // Prevent access to cookie from JavaScript
        cookie.setSecure(true);         // Only send cookie over HTTPS
        cookie.setPath("/");            // Cookie should be available across the entire domain
        cookie.setMaxAge(0);  // Set expiry time for the cookie (same as JWT expiration)
        cookie.setAttribute("SameSite", "None");
        response.addCookie(cookie);
        return expiredToken;
    }
}