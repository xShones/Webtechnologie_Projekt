package com.uni.project.repository;

import com.uni.project.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAccountName(String accountName);

    boolean existsByEmail(String email);

    boolean existsByAccountName(String accountName);

    Optional<User> findUserByEmail(String email);
}