package com.project.expense.controller;

import com.project.expense.dto.CreateExpensedto;
import com.project.expense.entity.Expense;
import com.project.expense.service.CategoryService;
import com.project.expense.service.ExpenseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody CreateExpensedto createExpense) {
        Expense createdExpense = expenseService.createExpense(createExpense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("expenseId") Long expenseId) {
        Expense expense = expenseService.getExpenseById(expenseId);

        if (expense != null) {
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable("expenseId") Long expenseId) {
        boolean deleted = expenseService.deleteExpense(expenseId);

        if(deleted) {
            return new ResponseEntity<>("Expense deleted Successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Expense not found or unable to delete", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        
        if (!expenses.isEmpty()) {
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } else {
            return null;
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Expense>> getExpenseByStatus(@PathVariable("categoryId") Long categoryId) {
        List<Expense> expenses = categoryService.getAllExpensesForCategory(categoryId); 
        
        if (!expenses.isEmpty()) {
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
}

