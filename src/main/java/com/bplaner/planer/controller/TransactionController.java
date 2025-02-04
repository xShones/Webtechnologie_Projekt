package com.bplaner.planer.controller;

import com.bplaner.planer.dto.UserAccountBalanceDTO;
import com.bplaner.planer.entity.Transaction;
import com.bplaner.planer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    //http://localhost:8081/transactions/all
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    //http://localhost:8081/transactions/5
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping("/new")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionService.updateTransaction(id, transactionDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/user/{userId}")
    //http://localhost:8081/transactions/user/3
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    @GetMapping("/user/{userId}/date")
    //http://localhost:8081/transactions/user/3/date?startDate=2025-01-08&endDate=2025-01-09
    public List<Transaction> getTransactionsByUserIdAndDateBetween(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return transactionService.getTransactionsByUserIdAndDateBetween(userId, startDate, endDate);
    }

    @GetMapping("/user/{userId}/category")
    //http://localhost:8081/transactions/user/3/category?category=Gehalt
    public List<Transaction> getTransactionsByUserIdAndCategory(
            @PathVariable Long userId,
            @RequestParam String category) {
        return transactionService.getTransactionsByUserIdAndCategory(userId, category);
    }

    @GetMapping("/user/{userId}/type/date")
    //http://localhost:8081/transactions/user/3/type?type=Einnahme&startDate=2025-01-08&endDate=2025-01-09
    public List<Transaction> getTransactionsByUserIdAndTypeAndDateBetween(
            @PathVariable Long userId,
            @RequestParam String type,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return transactionService.getTransactionsByUserIdAndTypeAndDateBetween(userId, type, startDate, endDate);
    }
    @GetMapping("/{userId}/balance")
    //http://localhost:8081/transactions/3/balance
    public UserAccountBalanceDTO getAccountBalance(@PathVariable Long userId) {
        return transactionService.getAccountBalanceByUserId(userId);
    }
}
