package com.uni.project.service;

import com.uni.project.entity.User;
import com.uni.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //User registration
    public User registerUser(String username, String email, String password) {
        validateUser(username, email); // Verschobene Logik
        User user = new User(username, email, password);
        return userRepository.save(user);
    }
    private void validateUser(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Der Benutzername ist bereits vergeben.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Die E-Mail-Adresse ist bereits registriert.");
        }
    }

    // Search for users by username
    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    // Search for users by email (e.g. for password recovery)
    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("Benutzer mit ID " + userId + " nicht gefunden."));
    }


    @Override
    public User createUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        User userObj= userRepository.findById(userId).get();
        if(userObj !=null){
            userObj.setUsername(user.getUsername());
            userObj.setEmail(user.getEmail());
            userObj.setPassword(user.getPassword());
        }
        return userRepository.save(userObj);
    }

    @Override
    public String deleteUser(Long userId) {
        User userObj = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer mit ID " + userId + " nicht gefunden."));
        userRepository.delete(userObj);
        return "User erfolgreich gelöscht.";
    }


}
