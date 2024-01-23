package com.project.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.expense.dto.CreateBudgetDto;
import com.project.expense.entity.Budget;
import com.project.expense.repository.BudgetRepository;
import com.project.expense.repository.BudgetTypeRepository;
import com.project.expense.repository.CategoryRepository;

@Service
public class BudgetService {
    
    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetTypeRepository budgetTypeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Budget createBudget(@RequestBody CreateBudgetDto createBudget) {
        Budget budget = new Budget();
        budget.setTypeId(budgetTypeRepository.findById(createBudget.getBudgetTypeId()).orElseThrow());
        budget.setEntityId(createBudget.getEntityId());
        budget.setCategoryId(categoryRepository.findById(createBudget.getBudgetCategoryId()).orElseThrow());
        budget.setStartDate(createBudget.getBudgetStartDate());
        budget.setEndDate(createBudget.getBudgetEndDate());
        budget.setAmount(createBudget.getBudgetAmount());
        budget.setUsedAmount(createBudget.getUsedBudgetAmount());

        return budgetRepository.save(budget);
    }

}
