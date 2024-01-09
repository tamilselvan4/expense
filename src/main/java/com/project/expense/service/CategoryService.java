package com.project.expense.service;

import com.project.expense.entity.ExpenseCategory;
import com.project.expense.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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

}
