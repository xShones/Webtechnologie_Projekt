package com.uni.project.dto;

public class BudgetDTO {
    private Long id;
    private Double monthlyLimit;
    private Double currentSpending;

    // Konstruktoren
    public BudgetDTO() {}

    public BudgetDTO(Long id, Double monthlyLimit, Double currentSpending) {
        this.id = id;
        this.monthlyLimit = monthlyLimit;
        this.currentSpending = currentSpending;
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(Double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public Double getCurrentSpending() {
        return currentSpending;
    }

    public void setCurrentSpending(Double currentSpending) {
        this.currentSpending = currentSpending;
    }
}
