package com.project.expense.repository;

import com.project.expense.entity.Expense;
import com.project.expense.entity.ExpenseCategory;
import com.project.expense.entity.ExpenseStatus;
import com.project.expense.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUser(User user);

    List<Expense> findAllByStatus(ExpenseStatus statusCategory);

    List<Expense> findAllByCategory(ExpenseCategory expenseCategory);

}