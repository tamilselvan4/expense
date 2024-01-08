package com.project.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateExpensedto {

    private Long userId;
    private Long categoryId;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private byte[] file;
    private Long stateId;
    private Long administratorPerson;
    private String rejectedReason;
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
    public Long getStateId() {
        return stateId;
    }
    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
    
    public Long getAdministratorPerson() {
        return administratorPerson;
    }
    public void setAdministratorPerson(Long administratorPerson) {
        this.administratorPerson = administratorPerson;
    }
    public String getRejectedReason() {
        return rejectedReason;
    }
    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    
    
}

