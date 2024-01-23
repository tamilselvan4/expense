package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.expense.entity.BudgetType;

public interface BudgetTypeRepository extends JpaRepository<BudgetType, Long>{
    
}
