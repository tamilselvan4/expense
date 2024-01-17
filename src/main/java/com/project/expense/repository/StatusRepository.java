package com.project.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.expense.entity.StatusCategory;

@Repository
public interface StatusRepository extends JpaRepository<StatusCategory, Long> {
    
}

