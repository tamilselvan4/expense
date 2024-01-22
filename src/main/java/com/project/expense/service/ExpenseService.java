package com.project.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.expense.dto.CreateExpensedto;
import com.project.expense.entity.Expense;
import com.project.expense.entity.ExpenseStatus;
import com.project.expense.entity.User;
import com.project.expense.repository.CategoryRepository;
import com.project.expense.repository.ExpenseRepository;
import com.project.expense.repository.StatusRepository;
import com.project.expense.repository.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    public Expense createExpense(CreateExpensedto createExpense) {
        Expense expense = new Expense();
        expense.setUser(userRepository.findById(createExpense.getUserId()).orElseThrow());
        expense.setCategory(categoryRepository.findById(createExpense.getCategoryId()).orElseThrow());
        expense.setAmount(createExpense.getAmount());
        expense.setDate(createExpense.getDate());
        expense.setDescription(createExpense.getDescription());
        expense.setStatus(statusRepository.findById(createExpense.getStatusId()).orElseThrow());

        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(Long expenseId) {
        return expenseRepository.findById(expenseId).orElse(null);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<Expense> getAllExpensesForUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            return expenseRepository.findAllByUser(user.get());
        }

        return null;
    }

    public boolean deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElse(null);

        if(expense != null) {
            expenseRepository.delete(expense);
            return true;
        }
        return false;
    }

    public List<Expense> getAllExpensesByStatus(Long status_id) {

        Optional<ExpenseStatus> expensesByStatusId = statusRepository.findById(status_id);

        if (expensesByStatusId.isPresent()) {
            return expenseRepository.findAllByStatus(expensesByStatusId.get());
        }

        return null;
    }
    
}

