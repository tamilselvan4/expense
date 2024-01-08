package com.project.expense.repository;

import com.project.expense.entity.Expense;
import com.project.expense.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<User, Long> {

    List<Expense> findAllByUserId(Long userId);

    Expense save(Expense expense);
}