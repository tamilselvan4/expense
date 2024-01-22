package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.expense.entity.ExpenseStatus;

@Repository
public interface StatusRepository extends JpaRepository<ExpenseStatus, Long> {
    
}

