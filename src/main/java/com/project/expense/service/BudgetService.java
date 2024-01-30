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

    // public BigDecimal checkAvailableBalance(Long entityId) {
    //     return budgetRepository.findByEntityId(entityId).getAmount();
    // }

    // public Boolean checkAvailableBalanceForCompany(BigDecimal amount, Long entityId) {
    //     Optional<Company> company = companyRepository.findById(entityId);
        
    //     if(company.isPresent()) {
    //         List<Budget> companyBudgetsByEntityId = budgetRepository.findByEntityIdOrderByEntityId(entityId);
    //         List<Budget> companyBudgetsByTypeId = getAllBudgetByTypeId(entityId);

    //         List<Budget> companyBudget = new ArrayList<>(companyBudgetsByEntityId);
    //         companyBudget.retainAll(companyBudgetsByTypeId);

    //         Budget lastBudget = companyBudget.get(companyBudget.size() - 1);
    //         if(lastBudget.getUsedAmount() == null) {
    //             lastBudget.setUsedAmount(BigDecimal.valueOf(0));
    //         }
    //         BigDecimal remainingBalance = (lastBudget.getAmount()).subtract(lastBudget.getUsedAmount());
    //         if(amount.compareTo(remainingBalance)<0){
    //             lastBudget.setUsedAmount(amount);
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public Boolean checkAvailableBalance(BigDecimal expenseAmount, Long entityId, Long typeId) {
        Optional<User> user = userRepository.findById(entityId);
        Optional<Company> company = companyRepository.findById(entityId);
        if((typeId == 2 && user.isPresent()) || (typeId == 1 && company.isPresent())) {
            BudgetType type = budgetTypeRepository.findById(typeId).orElseThrow();
            List<Budget> budgets = budgetRepository.findAllByEntityIdAndTypeId(entityId, type);

            if(budgets.isEmpty()) {
                return true;
            }
            Budget lastBudget = budgets.get(budgets.size()-1);

            if(lastBudget.getUsedAmount() == null) {
                lastBudget.setUsedAmount(BigDecimal.valueOf(0));
            }
            BigDecimal remainingBalance = (lastBudget.getAmount()).subtract(lastBudget.getUsedAmount());

            if(expenseAmount.compareTo(remainingBalance)<0){
                return true;
            }
        }
        return false;
    }

    // public List<Budget> getAllBudgetByTypeId(Long typeId) {
    //     Optional<BudgetType> type = budgetTypeRepository.findById(typeId);

    //     if(type.isPresent()){
    //         return budgetRepository.findAllBudgetByTypeId(type.get());
    //     }

    //     return null;
    // }

    public Budget getBudgetByEntityAndType(Long entityId, Long typeId) {
        Optional<Company> company = companyRepository.findById(entityId);
        Optional<User> user = userRepository.findById(entityId);

        if(company.isPresent() || user.isPresent()) {
            BudgetType type = budgetTypeRepository.findById(typeId).orElseThrow();
            List<Budget> budgets = budgetRepository.findAllByEntityIdAndTypeId(entityId, type);
            return budgets.get(budgets.size() - 1);
        }

        else {
            return null;
        }
    }

    public void updateAvailableBalance(BigDecimal amount, Long entityId, Long typeId) {
        BudgetType type = budgetTypeRepository.findById(typeId).orElseThrow();
        List<Budget> budgets = budgetRepository.findAllByEntityIdAndTypeId(entityId, type);

        if(! budgets.isEmpty()) {
            Budget lastBudget = budgets.get(budgets.size()-1);
            lastBudget.setUsedAmount(lastBudget.getUsedAmount().add(amount));
        }

        
    }

    // public Budget getAllBudgetByUserId(Long entityId) {
    //     Optional<User> user = userRepository.findById(entityId);
    //     if(user.isPresent()) {
    //         return budgetRepository.findByEntityId(entityId);
    //     }

    //     return null;
    // }

}
