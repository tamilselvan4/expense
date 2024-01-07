package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.expense.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    
}
