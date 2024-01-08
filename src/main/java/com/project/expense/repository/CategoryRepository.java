package com.project.expense.repository;

import com.project.expense.entity.ExpenseCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    
}
