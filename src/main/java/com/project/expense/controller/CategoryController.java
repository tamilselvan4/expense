package com.project.expense.controller;

import com.project.expense.entity.ExpenseCategory;
import com.project.expense.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<ExpenseCategory>> getAllCategories() {
        List<ExpenseCategory> categories = categoryService.getAllCategories();

        if (!categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestParam String categoryName) {
        ExpenseCategory newCategory = new ExpenseCategory();
        newCategory.setCategoryName(categoryName);

        categoryService.addCategory(newCategory);

        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        boolean deleted = categoryService.deleteCategory(categoryId);

        if (deleted) {
            return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }
}