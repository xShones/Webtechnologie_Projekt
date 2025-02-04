package com.uni.project.controller;

import com.uni.project.entity.Category;
import com.uni.project.service.CategoryService;
import com.uni.project.service.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

 /*   //http://localhost:8082/categories/new
    {
        "name": "Lebensmittel",
            "description": "Kategorien für Einkäufe im Supermarkt"
    }*/
    @PostMapping("/new")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    //http://localhost:8082/categories/1
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    //http://localhost:8082/categories/all
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

/*    http://localhost:8082/categories/2
    {
        "name": "Lebensmittel",
            "description": "Alle Ausgaben für den täglichen Bedarf"
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    //http://localhost:8082/categories/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category with ID " +id+ " was successfully deleted.");
        } catch (CategoryServiceImp.CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()
            );
        }
    }

    //http://localhost:8082/categories/search?name=Lebensmittel
    @GetMapping("/search")
    public ResponseEntity<List<Category>> getCategoriesByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.getCategoriesByName(name));
    }
    //http://localhost:8082/categories/description?keyword=Supermarkt
    @GetMapping("/description")
    public ResponseEntity<List<Category>> searchByDescription(@RequestParam String keyword) {
        return ResponseEntity.ok(categoryService.searchByDescription(keyword));
    }
}
