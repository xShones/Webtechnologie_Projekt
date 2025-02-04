package com.uni.project.service;

import com.uni.project.dto.TransactionDTO;
import com.uni.project.dto.UserAccountBalanceDTO;
import com.uni.project.entity.Transaction;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getAllTransactionDTOs();
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
    List<Transaction> getTransactionsByUserIdAndTypeAndDateBetween(Long userId, String type, LocalDate startDate, LocalDate endDate);
    List<Transaction> getTransactionsByUserId(Long userId);
    List<Transaction> getTransactionsByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    //List<Transaction> getTransactionByCategoryId(Long categoryId);
  //  List<Transaction> getTransactionsByUserIdAndCategory(Long userId, Category category);

  //  List<Transaction> getTransactionsByUserIdAndCategory(Long userId, String category);

    UserAccountBalanceDTO getAccountBalanceByUserId(Long userId);
}