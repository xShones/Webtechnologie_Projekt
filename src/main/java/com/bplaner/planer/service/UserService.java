package com.bplaner.planer.service;

import com.bplaner.planer.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long UserId, User user);
    String deleteUser(Long UserId);
    public UserDetails loadUserByUsername(String username);
}
