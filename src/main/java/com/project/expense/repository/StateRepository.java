package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.expense.entity.StateCategory;

@Repository
public interface StateRepository extends JpaRepository<StateCategory, Long> {
    
}

