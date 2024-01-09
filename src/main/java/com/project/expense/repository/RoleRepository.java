package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.expense.entity.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    
}

