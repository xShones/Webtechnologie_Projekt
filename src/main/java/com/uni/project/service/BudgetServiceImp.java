package com.uni.project.service;

import com.uni.project.entity.Budget;
import com.uni.project.exception.BudgetNotFoundException;
import com.uni.project.repository.BudgetRepository;
import org.springframework.stereotype.Service;


@Service
public class BudgetServiceImp  implements BudgetService{
    private final BudgetRepository budgetRepository;

    public BudgetServiceImp(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }
    @Override
    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id).orElse(null);
    }

    @Override
    public Budget getBudgetByUserId(Long userId) {
        return budgetRepository.findByUserId(userId)
                .orElseThrow(() -> new BudgetNotFoundException("Budget für User mit ID " + userId+ " nicht gefunden"));
    }

    @Override
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public Budget updateBudget(Long id, Budget budget) {
        Budget existingBudget = getBudgetById(id);
        if (existingBudget != null) {
            existingBudget.setMonthlyLimit(budget.getMonthlyLimit());
            existingBudget.setCurrentSpending(budget.getCurrentSpending());
            return budgetRepository.save(existingBudget);
        }
        return null;
    }

    @Override
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}