package com.bplaner.planer.repository;

import com.bplaner.planer.dto.UserAccountBalanceDTO;
import com.bplaner.planer.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    // Retrieve all transaction of a user
    List<Transaction> findByUserId(Long userId);


    // Retrieving a user´s transactions in a specific period of time
    // SELECT * FROM transaction
    // WHERE user_id = ?
    // AND date BETWEEN ? AND ?;
    List<Transaction> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    // Retrieving transactions by category for a user
    // SELECT * FROM transaction
    // WHERE user_id = ? AND type = ?;
    List<Transaction> findByUserIdAndCategory(Long userId, String type); // "Einnahme" oder "Ausgabe"

    // Calculating a user´s total expenses/income in a given period
    // SELECT * FROM transaction
    // WHERE user_id = ?
    // AND type = ?
    // AND date BETWEEN ? AND ?;
    List<Transaction> findByUserIdAndTypeAndDateBetween(Long userId, String type, LocalDate starDate,
    LocalDate endDate);
    @Query(value = """
        SELECT
            u.username AS username,
            u.id AS userId,
            (
                COALESCE(SUM(CASE WHEN t.type = 'Einnahme' THEN t.amount ELSE 0 END), 0) -
                COALESCE(SUM(CASE WHEN t.type = 'Ausgabe' THEN t.amount ELSE 0 END), 0)
            ) AS accountBalance
        FROM
            user u
        LEFT JOIN
            transaction t ON u.id = t.user_id
        WHERE
            u.id = :userId
        GROUP BY
            u.username, u.id
        """, nativeQuery = true)
    Optional<UserAccountBalanceDTO> findAccountBalanceByUserId(@Param("userId") Long userId);

}
