package com.project.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.expense.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Optional<Company> findById(Company companyId);
    
}

