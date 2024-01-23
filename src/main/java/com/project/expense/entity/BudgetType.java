package com.project.expense.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "budget_type")
public class BudgetType {
    @Id
    @Column(name = "budget_type_id")
    private Long budgetTypeId;

    @Column(name = "type_name")
    private String typeName;

    public Long getBudgetTypeId() {
        return budgetTypeId;
    }

    public void setBudgetTypeId(Long budgetTypeId) {
        this.budgetTypeId = budgetTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
}
