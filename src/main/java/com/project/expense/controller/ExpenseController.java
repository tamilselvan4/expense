package com.project.expense.controller;

import com.project.expense.dto.CreateExpensedto;
import com.project.expense.entity.Expense;
import com.project.expense.service.ExpenseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody CreateExpensedto createExpense) {
        Expense createdExpense = expenseService.createExpense(createExpense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getAllExpensesForUser(@PathVariable Long userId) {
        List<Expense> userExpenses = expenseService.getAllExpensesForUser(userId);

        if (!userExpenses.isEmpty()) {
            return new ResponseEntity<>(userExpenses, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
}

