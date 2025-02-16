package com.uni.project.service;

import com.uni.project.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long UserId, User user);
    String deleteUser(Long UserId);
}