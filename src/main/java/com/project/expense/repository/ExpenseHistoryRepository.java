package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.expense.entity.ExpenseHistory;

public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistory, Long> {
    
}
