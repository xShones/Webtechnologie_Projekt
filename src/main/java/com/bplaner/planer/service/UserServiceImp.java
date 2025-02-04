package com.bplaner.planer.service;

import com.bplaner.planer.entity.User;
import com.bplaner.planer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //User registration
    public User registerUser(String username, String email, String password){
        if (userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Der Benutzername ist bereits vergeben.");
        }
        if (userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Die E-Mail-Adresse ist bereits registriert.");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        User userObj = userRepository.findById(userId).get();
        if (userObj !=null){
            userRepository.delete(userObj);
        }
        return "User is deleted Successfully from database for id: " +userId;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Benutzer nicht gefunden: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                user.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        );
    }
}
