package com.project.expense.service;

import com.project.expense.entity.Expense;
import com.project.expense.entity.ExpenseCategory;
import com.project.expense.repository.CategoryRepository;
import com.project.expense.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<ExpenseCategory> getAllCategories() {
       return categoryRepository.findAll();
    }

    public void addCategory(ExpenseCategory category) {
        categoryRepository.save(category);
    }

    public boolean deleteCategory(Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        } else {
            return false;
        }
    }

    public List<Expense> getAllExpensesByCategory(Long categoryId) {

        Optional<ExpenseCategory> expenseByCategoryId = categoryRepository.findById(categoryId);

        if (expenseByCategoryId.isPresent()) {
            return expenseRepository.findAllByCategory(expenseByCategoryId.get());
        }

        return null;
    }

    public boolean isCategoryExists(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }

}
