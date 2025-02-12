package com.uni.project.service;

import com.uni.project.entity.Category;
import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    List<Category> getCategoriesByName(String name);
    List<Category> searchByDescription(String keyword);
    List<Category> getCategoriesByTransactionId(Long transactionId);
    boolean existsById(Long id);
}
