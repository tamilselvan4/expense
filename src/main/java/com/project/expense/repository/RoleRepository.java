package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.expense.entity.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    
}

