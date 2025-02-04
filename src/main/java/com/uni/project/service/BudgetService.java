package com.uni.project.service;

import com.uni.project.entity.Budget;

public interface BudgetService {
    Budget getBudgetById(Long id);

    Budget getBudgetByUserId(Long userId);

    Budget createBudget(Budget budget);

    Budget updateBudget(Long id, Budget budget);

    void deleteBudget(Long id);
}
