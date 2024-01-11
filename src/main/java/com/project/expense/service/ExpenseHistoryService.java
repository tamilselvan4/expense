package com.project.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.expense.entity.Expense;
import com.project.expense.entity.ExpenseHistory;
import com.project.expense.repository.ExpenseHistoryRepository;
import com.project.expense.repository.ExpenseRepository;
import com.project.expense.repository.StateRepository;
import com.project.expense.repository.UserRepository;

@Service
public class ExpenseHistoryService {

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseHistoryRepository expenseHistoryRepository;

    public ExpenseHistory createExpenseHistory(Expense createdExpense) {
        ExpenseHistory expenseHistory = new ExpenseHistory();

        Long userId = createdExpense.getUser().getUserId();
        Long stateId = createdExpense.getState().getStateId();

        expenseHistory.setExpense(expenseRepository.findById(createdExpense.getExpenseId()).orElseThrow());
        expenseHistory.setEmployee(userRepository.findById(userId).orElseThrow());
        expenseHistory.setState(stateRepository.findById(stateId).orElseThrow());
        
        return expenseHistoryRepository.save(expenseHistory);
    }
    
}
