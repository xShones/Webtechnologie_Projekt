package com.uni.project.controller;

import com.uni.project.entity.Budget;
import com.uni.project.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    //{
    //    "monthlyLimit": 1000.0,
    //    "currentSpending": 200.0,
    //    "user": {
    //        "id": 1
    //    }
    //}
    // CREATE: Neues Budget erstellen
    @PostMapping(value = "/new")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget createdBudget = budgetService.createBudget(budget);
        return ResponseEntity.ok(createdBudget);
    }

    // READ: Budget anhand der ID abrufen
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Budget budget = budgetService.getBudgetById(id);
        if (budget != null) {
            return ResponseEntity.ok(budget);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // READ: Budget eines Benutzers anhand der User-ID abrufen
    @GetMapping("/user/{userId}")
    public ResponseEntity<Budget> getBudgetByUserId(@PathVariable Long userId) {
        Budget budget = budgetService.getBudgetByUserId(userId);
        return ResponseEntity.ok(budget);
    }


/*
    {
        "monthlyLimit": 2530.0,
            "currentSpending": 1300.0,
            "user": {
        "id": 1
    }
    }
*/
    // UPDATE: Budget aktualisieren
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        Budget updatedBudget = budgetService.updateBudget(id, budget);
        if (updatedBudget != null) {
            return ResponseEntity.ok(updatedBudget);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Budget löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}
