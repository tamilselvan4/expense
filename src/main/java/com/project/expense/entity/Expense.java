package com.project.expense.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Expenses")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateCategory state;

    @ManyToOne
    @JoinColumn(name = "administrator_person_id")
    private User administratorPerson;

    @Column(name = "rejected_reason")
    private String rejectedReason;

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public StateCategory getState() {
        return state;
    }

    public void setState(StateCategory s) {
        this.state = s;
    }

    public User getAdministratorPerson() {
        return administratorPerson;
    }

    public void setAdministratorPerson(User administratorPerson) {
        this.administratorPerson = administratorPerson;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public enum ExpenseStatus {
        CREATED, REQUESTED, ACCEPTED, REJECTED
    }
}


