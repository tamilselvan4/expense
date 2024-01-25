package com.project.expense.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="budget")
public class Budget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Long budgetId;

    @ManyToOne
    @JoinColumn(name = "budget_type_id")
    private BudgetType budgetType;

    @Column(name = "budget_entity_id")
    private long entityId;

    @ManyToOne
    @JoinColumn(name = "budget_category_id")
    private ExpenseCategory categoryId;

    @Column(name = "budget_start_date")
    private Date startDate;

    @Column(name = "budget_end_date")
    private Date endDate;

    @Column(name = "budget_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "used_budget_amount")
    private BigDecimal usedAmount;

    public long getBudgetId() {
        return budgetId;
    }
    public void setBudgetId(long budgetId) {
        this.budgetId = budgetId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public ExpenseCategory getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(ExpenseCategory categoryId) {
        this.categoryId = categoryId;
    }
    public BudgetType getTypeId() {
        return budgetType;
    }
    public void setTypeId(BudgetType typeId) {
        this.budgetType = typeId;
    }
    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }
    public BigDecimal getUsedAmount() {
        return usedAmount;
    }
    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }
    public long getEntityId() {
        return entityId;
    }
    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}
