package com.project.expense.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.expense.dto.CreateBudgetDto;
import com.project.expense.entity.Budget;
import com.project.expense.entity.BudgetType;
import com.project.expense.entity.Company;
import com.project.expense.entity.Expense;
import com.project.expense.entity.User;
import com.project.expense.repository.BudgetRepository;
import com.project.expense.repository.BudgetTypeRepository;
import com.project.expense.repository.CategoryRepository;
import com.project.expense.repository.CompanyRepository;
import com.project.expense.repository.UserRepository;

@Service
public class BudgetService {
    
    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetTypeRepository budgetTypeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

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

    public BigDecimal checkAvailableBalance(Long entityId) {
        Budget budget = budgetRepository.findByEntityId(entityId);
        return budget.getAmount();
    }

    public Boolean checkAvailableBalanceForCompany(BigDecimal amount, Long entityId) {
        Optional<Company> company = companyRepository.findById(entityId);
        
        if(company.isPresent()) {
            Budget budget = budgetRepository.findByEntityId(entityId);
            if(budget.getUsedAmount() == null) {
                budget.setUsedAmount(BigDecimal.valueOf(0));
            }
            BigDecimal remainingBalance = (budget.getAmount()).subtract(budget.getUsedAmount());
            if(amount.compareTo(remainingBalance)<0){
                budget.setUsedAmount(amount);
                return true;
            }
        }
        return false;
    }

    public Boolean checkAvailableBalanceForUser(BigDecimal expenseAmount, Long entityId, Long typeId) {
        Optional<User> user = userRepository.findById(entityId);
        
        if(user.isPresent()) {
            List<Budget> userBudgets = budgetRepository.findByEntityIdOrderByEntityId(entityId);
            // List<Budget> userBudgets2 = budgetRepository.findByTypeIdOrderByTypeId(typeId);
            // List<Budget> userBudgets2 = getAllBudgetByType(typeId);

            Budget lastBudget = userBudgets.get(userBudgets.size()-1);
            BudgetType b = lastBudget.getTypeId();
            System.out.println(b.getBudgetTypeId().toString());
            // Budget budget = budgetRepository.findByEntityId(entityId);

            if(lastBudget.getUsedAmount() == null) {
                lastBudget.setUsedAmount(BigDecimal.valueOf(0));
            }
            BigDecimal remainingBalance = (lastBudget.getAmount()).subtract(lastBudget.getUsedAmount());

            if(expenseAmount.compareTo(remainingBalance)<0){
                lastBudget.setUsedAmount(lastBudget.getUsedAmount().add(expenseAmount));
                return true;
            }
        }
        return false;
    }

    // public List<Budget> getAllBudgetByType(Long typeId) {
    //     Optional<BudgetType> type = budgetTypeRepository.findById(typeId);

    //     if(type.isPresent()){
    //         return budgetRepository.findAllByType(type.get());
    //     }

    //     return null;
    // }

    public Budget getAllBudgetByCompanyId(Long entityId) {
        Optional<Company> company = companyRepository.findById(entityId);
        if(company.isPresent()) {
            return budgetRepository.findByEntityId(entityId);
        }

        return null;
    }

    public Budget getAllBudgetByUserId(Long entityId) {
        Optional<User> user = userRepository.findById(entityId);
        if(user.isPresent()) {
            return budgetRepository.findByEntityId(entityId);
        }

        return null;
    }

}
