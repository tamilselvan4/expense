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
@Table(name = "expense")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "expense_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory category;

    @Column(name = "expense_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "expense_date", nullable = false)
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "expense_status_id")
    private ExpenseStatus status;

    @Column(name = "invoice_no", nullable = true)
    private int invoiceNo;

    @Column(name = "expense_description")
    private String description;

    @Column(name = "expense_file")
    private byte[] file;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus s) {
        this.status = s;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

}


