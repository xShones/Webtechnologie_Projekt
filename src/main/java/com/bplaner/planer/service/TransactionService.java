package com.bplaner.planer.service;

import com.bplaner.planer.dto.UserAccountBalanceDTO;
import com.bplaner.planer.entity.Transaction;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
    List<Transaction> getTransactionsByUserIdAndTypeAndDateBetween(Long userId, String type, LocalDate startDate, LocalDate endDate);
    List<Transaction> getTransactionsByUserId(Long userId);
    List<Transaction> getTransactionsByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    List<Transaction> getTransactionsByUserIdAndCategory(Long userId, String category);
    UserAccountBalanceDTO getAccountBalanceByUserId(Long userId);
}
