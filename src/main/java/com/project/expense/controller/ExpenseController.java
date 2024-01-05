package com.project.expense.controller;

import com.project.expense.dto.CreateExpenseRequest;
import com.project.expense.entity.Expense;
import com.project.expense.service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody CreateExpenseRequest createExpenseRequest) {
        Expense createdExpense = expenseService.createExpense(createExpenseRequest);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }
}

