package com.project.expense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.expense.entity.Expense;
import com.project.expense.entity.ExpenseCategory;
import com.project.expense.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    List<Expense> findAllByUserId(Long userId);
    
}
