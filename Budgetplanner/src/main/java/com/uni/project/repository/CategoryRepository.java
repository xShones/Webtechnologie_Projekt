package com.uni.project.repository;

import com.uni.project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Suche nach einer Kategorie anhand des Namens
    Optional<Category> findByName(String name);

    // Suche nach allen Kategorien mit einem bestimmten Beschreibungstext (Teilwort)
    //List<Category> findByDescriptionContainingIgnoreCase(String keyword);

    // Suche nach Kategorien, die mit einer spezifischen Transaktion verbunden sind
    List<Category> findByTransactions_Id(Long transactionId);

    // Custom Query: Suche nach Kategorien, deren Name teilweise übereinstimmt
    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name%")
    List<Category> searchByNameLike(@Param("name") String name);

    //@Query(value = "SELECT * FROM categories WHERE description ILIKE %:keyword%", nativeQuery = true)
    //List<Category> searchByDescriptionNative(@Param("keyword") String keyword);
}

