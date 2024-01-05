package com.project.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.expense.dto.CreateExpenseRequest;
import com.project.expense.entity.Expense;
import com.project.expense.repository.ExpenseRepository;
import com.project.expense.repository.UserRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    public Expense createExpense(CreateExpenseRequest createExpenseRequest) {
        Expense expense = new Expense();
        expense.setUser(userRepository.findById(createExpenseRequest.getUserId()).orElseThrow());
        // expense.setCategory(expenseCategoryRepository.findById(createExpenseRequest.getCategoryId()).orElseThrow());
        expense.setAmount(createExpenseRequest.getAmount());
        expense.setDate(createExpenseRequest.getDate());
        expense.setDescription(createExpenseRequest.getDescription());

        return expenseRepository.save(expense);
    }
}

