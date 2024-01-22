package com.project.expense.dto;

public class CreateUserDto {

    private Long userId;
    private String userName;
    private String email;
    private String password;
    private Long userRole;
    private Long companyId;
    private Boolean isActive;
    private Long adminId;
    
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
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public Long getAdminId() {
        return adminId;
    }
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    
}

