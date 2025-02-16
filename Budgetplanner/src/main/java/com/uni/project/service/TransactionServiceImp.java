package com.uni.project.service;

import com.uni.project.dto.UserAccountBalanceDTO;
//import com.uni.project.entity.Category;
import com.uni.project.entity.Transaction;
import com.uni.project.entity.User;
import com.uni.project.repository.TransactionRepository;
import com.uni.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionServiceImp(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long transacationId) {
        return transactionRepository.findById(transacationId)
                .orElseThrow(() -> new NoSuchElementException("Transaction not found for ID: " + transacationId));
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        // Benutzer aus der Datenbank holen
        Long userId = transaction.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));

        // Benutzer der Transaktion setzen
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction transactionObj = transactionRepository.findById(id).get();

        if (transactionObj !=null){
            transactionObj.setAmount(transaction.getAmount());
            transactionObj.setType(transaction.getType());
            transactionObj.setDate(transaction.getDate());
            transactionObj.setUser(transaction.getUser());
        }

        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getTransactionsByUserIdAndTypeAndDateBetween(Long userId, String type, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByUserIdAndTypeAndDateBetween(userId,type,startDate,endDate);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
//
//    @Override
//    public List<Transaction> getTransactionByCategoryId(Long categoryId){
//        return transactionRepository.findByCategoryId(categoryId);
//    }

    @Override
    public List<Transaction> getTransactionsByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate );
    }
/*
    @Override
    public List<Transaction> getTransactionsByUserIdAndCategory(Long userId, Category category) {
        return List.of();
    }


    @Override
    public List<Transaction> getTransactionsByUserIdAndCategory(Long userId, String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        return transactionRepository.findByUserIdAndCategory(userId, category);
    }
*/
    @Override
    public UserAccountBalanceDTO getAccountBalanceByUserId(Long userId) {
        return transactionRepository.findAccountBalanceByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found or no transactions available"));}
}
