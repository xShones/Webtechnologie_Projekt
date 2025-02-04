package com.bplaner.planer.repository;

import com.bplaner.planer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find user by username(e.g., for login)
    Optional<User> findByUsername(String username);

    // Check if a user with a specific email exists
    boolean existsByEmail(String email);

    // Check if a user with a specific username exists
    boolean existsByUsername(String username);

    // Find user by email (e.g., for password recovery)
    Optional<User> findByEmail(String email);
}
