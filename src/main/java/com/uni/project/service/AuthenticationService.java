package com.uni.project.service;

import com.uni.project.dto.AuthenticationResponse;
import com.uni.project.entity.User;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    void register(User user, HttpServletResponse response);

    void authenticate(User user, HttpServletResponse response);

    AuthenticationResponse logout(User user, HttpServletResponse response);
}