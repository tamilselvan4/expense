package com.project.expense.entity;

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
@Table(name="user_budget")
public class UserBudget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private long budgetId;

    @ManyToOne
    @JoinColumn(name = "budget_user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "budget_category_id", nullable = false)
    private ExpenseCategory categoryId;

    @Column(name = "budget_start_date")
    private Date startDate;

    @Column(name = "budget_end_date")
    private Date endDate;

    @Column(name = "budget_amount", nullable = false)
    private float amount;

    public long getBudgetId() {
        return budgetId;
    }
    public void setBudgetId(long budgetId) {
        this.budgetId = budgetId;
    }
    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
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
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public ExpenseCategory getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(ExpenseCategory categoryId) {
        this.categoryId = categoryId;
    }
    
}
