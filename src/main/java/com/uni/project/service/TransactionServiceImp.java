package com.uni.project.service;

import com.uni.project.dto.TransactionDTO;
import com.uni.project.dto.UserAccountBalanceDTO;
//import com.uni.project.entity.Category;
import com.uni.project.entity.Category;
import com.uni.project.entity.Transaction;
import com.uni.project.entity.User;
import com.uni.project.repository.TransactionRepository;
import com.uni.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public List<TransactionDTO> getAllTransactionDTOs() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transaction -> { TransactionDTO dto= new TransactionDTO();
            dto.setId(transaction.getId());
            dto.setAmount(transaction.getAmount());
            dto.setDate(transaction.getDate());
            dto.setType(transaction.getType());
            //zugriff auf Kategorie und setzen des Kategories-Name
            if (transaction.getCategory() != null){
                dto.setCategoryName(transaction.getCategory().getName());
            }
            else {
                dto.setCategoryName("Uncategorized"); //Fallback, falls keine Kategorie vorhanden ist
            }
            return dto;}).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return List.of();
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }

//    @Override
//    public Transaction getTransactionById(Long transacationId) {
//        List<Transaction> transactions = transactionRepository.findById(transacationId)
//
//    }

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
