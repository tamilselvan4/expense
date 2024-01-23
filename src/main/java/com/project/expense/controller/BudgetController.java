package com.project.expense.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.expense.dto.CreateBudgetDto;
import com.project.expense.entity.Budget;
import com.project.expense.service.BudgetService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;
    
    @PostMapping("/add")
    public ResponseEntity<Budget> postMethodName(@RequestBody CreateBudgetDto budget) {
        Budget newbudget = budgetService.createBudget(budget);
        return new ResponseEntity<>(newbudget, HttpStatus.OK);
    }
    
}
