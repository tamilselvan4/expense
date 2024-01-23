package com.project.expense.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class CreateBudgetDto {
    
    private Long budgetTypeId;
    private Long entityId;
    private Long budgetCategoryId;
    private Date budgetStartDate;
    private Date budgetEndDate;
    private BigDecimal budgetAmount;
    private BigDecimal usedBudgetAmount;

    public Long getBudgetTypeId() {
        return budgetTypeId;
    }
    public void setBudgetTypeId(Long budgetTypeId) {
        this.budgetTypeId = budgetTypeId;
    }
    public Long getBudgetCategoryId() {
        return budgetCategoryId;
    }
    public void setBudgetCategoryId(Long budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }
    public Date getBudgetStartDate() {
        return budgetStartDate;
    }
    public void setBudgetStartDate(Date budgetStartDate) {
        this.budgetStartDate = budgetStartDate;
    }
    public Date getBudgetEndDate() {
        return budgetEndDate;
    }
    public void setBudgetEndDate(Date budgetEndDate) {
        this.budgetEndDate = budgetEndDate;
    }
    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }
    public void setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }
    public BigDecimal getUsedBudgetAmount() {
        return usedBudgetAmount;
    }
    public void setUsedBudgetAmount(BigDecimal usedBudgetAmount) {
        this.usedBudgetAmount = usedBudgetAmount;
    }
    public Long getEntityId() {
        return entityId;
    }
    public void setEntityId(Long userId) {
        this.entityId = userId;
    }

}
