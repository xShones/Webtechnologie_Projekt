package com.uni.project.controller;

import com.uni.project.dto.TransactionDTO;
import com.uni.project.dto.UserAccountBalanceDTO;
import com.uni.project.entity.Transaction;
import com.uni.project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TransactionDTO>> getAllTransactionDTOs() {
        List<TransactionDTO> transactionDTOs = transactionService.getAllTransactionDTOs();
        return ResponseEntity.ok(transactionDTOs);
    }

    @GetMapping("/{id}")
    //http://localhost:8082/transactions/5
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

   /* http://localhost:8082/transactions/new
    {
        "type": "Einnahme",
            "amount": 200.0,
            "date": "2025-01-17",
            "user": {
        "id": 1
    },
        "category": {
        "id": 4
    }
    }*/
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

 /*   http://localhost:8082/transactions/5
    {
        "type": "Ausgabe",
            "amount": 100.0,
            "date": "2025-01-18",
            "user": {
        "id": 1
    },
        "category": {
        "id": 3
    }
    }*/
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionService.updateTransaction(id, transactionDetails);
    }

    //http://localhost:8082/transactions/5
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    //http://localhost:8082/transactions/user/3
    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    @GetMapping("/user/{userId}/date")
    //http://localhost:8082/transactions/user/3/date?startDate=2025-01-08&endDate=2025-01-19
    public List<Transaction> getTransactionsByUserIdAndDateBetween(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return transactionService.getTransactionsByUserIdAndDateBetween(userId, startDate, endDate);
    }

/*   @GetMapping("/user/{userId}/category")
    //http://localhost:8081/transactions/user/3/category?category=Gehalt
    public List<Transaction> getTransactionsByUserIdAndCategory(
            @PathVariable Long userId,
            @RequestParam Category category) {
        return transactionService.getTransactionsByUserIdAndCategory(userId, category);
    }*/

    //http://localhost:8082/transactions/user/3/type/date?type=Ausgabe&startDate=2025-01-01&endDate=2025-01-17
    @GetMapping("/user/{userId}/type/date")
    public List<Transaction> getTransactionsByUserIdAndTypeAndDateBetween(
            @PathVariable Long userId,
            @RequestParam String type,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return transactionService.getTransactionsByUserIdAndTypeAndDateBetween(userId, type, startDate, endDate);
    }
    @GetMapping("/{userId}/balance")
    //http://localhost:8082/transactions/3/balance
    public UserAccountBalanceDTO getAccountBalance(@PathVariable Long userId) {
        return transactionService.getAccountBalanceByUserId(userId);
    }
}
