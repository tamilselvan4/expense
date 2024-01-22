package com.project.expense.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense_status")
public class ExpenseStatus {
    
    @Id
    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "status_name", unique = true, nullable = false)
    private String statusName;

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    
}

