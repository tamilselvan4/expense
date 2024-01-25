package com.project.expense.controller;

import com.project.expense.dto.CreateExpensedto;
import com.project.expense.entity.Expense;
import com.project.expense.exception.ExpenseCreationException;
import com.project.expense.service.CategoryService;
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
    private ExpenseService expenseService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createExpense(
        @RequestBody CreateExpensedto createExpense,
        @RequestParam(required = false) Long typeId,
        @RequestParam(required = false) Long entityId ) {
            try {
            Expense createdExpense = expenseService.createExpense(createExpense,typeId,entityId);
                if (createdExpense == null) {
                    throw new ExpenseCreationException("Expense creation failed");
                }
                return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
                
            } catch (ExpenseCreationException e) {
                return new ResponseEntity<>("Input parameters are not applicable: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses( 
        @RequestParam(required = false) Long categoryId) {

        List<Expense> expenses = expenseService.getAllExpenses();

        if (categoryId != null) {
            expenses = categoryService.getAllExpensesByCategory(categoryId);
        } else {
            expenses = expenseService.getAllExpenses();
        }

        if (!expenses.isEmpty()) {
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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
    
}

