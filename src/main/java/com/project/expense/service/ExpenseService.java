package com.project.expense.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.expense.dto.CreateExpensedto;
import com.project.expense.entity.Expense;
import com.project.expense.entity.User;
import com.project.expense.repository.CategoryRepository;
import com.project.expense.repository.ExpenseRepository;
import com.project.expense.repository.StateRepository;
import com.project.expense.repository.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository CategoryRepository;
    private final StateRepository stateRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, CategoryRepository CategoryRepository, StateRepository stateRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.CategoryRepository = CategoryRepository;
        this.stateRepository = stateRepository;
    }

    public Expense createExpense(CreateExpensedto createExpense) {
        Expense expense = new Expense();
        expense.setUser(userRepository.findById(createExpense.getUserId()).orElseThrow());
        expense.setCategory(CategoryRepository.findById(createExpense.getCategoryId()).orElseThrow());
        expense.setAmount(createExpense.getAmount());
        expense.setDate(createExpense.getDate());
        expense.setDescription(createExpense.getDescription());
        expense.setState(stateRepository.findById(createExpense.getStateId()).orElseThrow());

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpensesForUser(Long userId) {
        return expenseRepository.findAllByUserId(userId);
    }

    // public List<Expense> getAllExpensesByUserId(Long userId) {
    //     User user = userRepository.findById(userId).orElse(null);

    //     if (user != null) {
    //         return user.getExpenses();
    //     } else {
    //         return null;
    //     }
    // }
    
    
}

