package com.project.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.expense.entity.Budget;
import com.project.expense.entity.BudgetType;

public interface BudgetRepository extends JpaRepository<Budget, Long>{

    Budget findByEntityId(Long entityId);

    List<Budget> findByEntityIdOrderByEntityId(Long entityId);

    // List<Budget> findAllByType(BudgetType typeId);

}
