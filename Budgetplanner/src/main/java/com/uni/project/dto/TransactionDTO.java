package com.uni.project.dto;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private String type;
    private Double amount;
    private LocalDate date;
    private String categoryName;
    private String description;

    // Konstruktoren, Getter und Setter
    public TransactionDTO() {}
    public TransactionDTO(Long id, String type, Double amount, LocalDate date, String categoryName, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
