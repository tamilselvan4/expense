package com.project.expense.dto;

import com.project.expense.entity.Company;

public class CreateUserRequest {

    private String userName;
    private String email;
    private String password;
    private int userRole;
    private Company companyId;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getUserRole() {
        return userRole;
    }
    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
    public Company getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }
    
}

