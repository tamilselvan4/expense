package com.project.expense.repository;

import com.project.expense.entity.ExpenseCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ExpenseCategory, Long> {

    boolean existsByCategoryName(String categoryName);
    
}
