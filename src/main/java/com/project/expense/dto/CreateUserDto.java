package com.project.expense.dto;

public class CreateUserDto {

    public Long UserId;
    private String userName;
    private String email;
    private String password;
    private Long userRole;
    private Long companyId;

    
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
    public Long getUserRole() {
        return userRole;
    }
    public void setUserRole(Long userRole) {
        this.userRole = userRole;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Long getUserId() {
        return UserId;
    }
    public void setUserId(Long userId) {
        UserId = userId;
    }
    
}

