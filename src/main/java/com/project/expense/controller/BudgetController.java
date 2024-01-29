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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;
    
    @PostMapping("/add")
    public ResponseEntity<Budget> addBudget(@RequestBody CreateBudgetDto budget) {
        Budget newbudget = budgetService.createBudget(budget);
        return new ResponseEntity<>(newbudget, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Budget> getBudget(
        @RequestParam Long typeId,
        @RequestParam Long entityId,
        @RequestParam(required = false) Long categoryId) {
            Budget budget = budgetService.getAllBudgetByEntityAndType(entityId, typeId);

            // if(typeId == 1){
            //     budget = budgetService.getAllBudgetByCompanyId(entityId);
            // }
            // else if(typeId == 2) {
            //     budget = budgetService.getAllBudgetByUserId(entityId);
            // }
            // else {
            //     return null;
            // }

        return new ResponseEntity<>(budget, HttpStatus.OK);
    }
    
}
