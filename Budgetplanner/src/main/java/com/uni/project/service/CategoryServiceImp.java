package com.uni.project.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uni.project.entity.Category;
import com.uni.project.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getCategoriesByName(String name) {
        return categoryRepository.findByName(name)
                .stream()
                .toList(); // Falls findByName eine Option liefert
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(category.getName());
        //existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public List<Category> getCategoriesByTransactionId(Long transactionId) {
        return categoryRepository.findByTransactions_Id(transactionId);
    }

    /*@Override
    public List<Category> searchByDescription(String keyword) {
        return categoryRepository.findByDescriptionContainingIgnoreCase(keyword);
    }
    public static class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }*/

}

