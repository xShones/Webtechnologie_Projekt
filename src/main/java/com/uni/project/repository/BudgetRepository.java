package com.uni.project.repository;

import com.uni.project.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    //Retrieve a user´s Budget
    //SELECT * From budget Where user_id = ?;
    Optional<Budget> findByUserId(Long userId);

    //check if a budget exists for a user
    boolean existsByUserId(Long userId);

}
