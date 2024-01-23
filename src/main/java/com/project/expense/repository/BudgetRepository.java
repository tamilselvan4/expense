package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.expense.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long>{
    
}
