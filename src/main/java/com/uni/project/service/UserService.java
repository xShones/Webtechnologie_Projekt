package com.uni.project.service;

import com.uni.project.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByEmail(String email);
    User createUser(User user);
    User updateUser(Long UserId, User user);
    String deleteUser(Long UserId);
}