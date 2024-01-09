package com.project.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.expense.dto.CreateUserDto;
import com.project.expense.entity.User;
import com.project.expense.repository.CompanyRepository;
import com.project.expense.repository.RoleRepository;
import com.project.expense.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public User createUser(CreateUserDto createUser) {
        User u = new User();
        u.setUserName(createUser.getUserName());
        u.setEmail(createUser.getEmail());
        u.setPassword(createUser.getPassword());
        u.setCompany(companyRepository.findById(createUser.getCompanyId()).orElseThrow());
        u.setUserRole(roleRepository.findById(createUser.getUserRole()).orElseThrow());

        return userRepository.save(u);
    }

    public boolean isUserExists(String string) {
        return userRepository.existsByEmail(string);
    }
    
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, CreateUserDto updatedUser) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if(existingUser != null) {
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            // existingUser.setCompany(updatedUser.getCompanyId());
            // existingUser.setUserRole(updatedUser.getUserRole());

            return userRepository.save(existingUser);
        }
        else {
            return null;
        }
    }

    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user !=null && verifyPassword(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    private boolean verifyPassword(String inputPassword, String password) {
        return inputPassword.equals(password);
    }

    public boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            userRepository.delete(user);
            return true;
        } 
        else {
            return false;
        }
    }

    // public ArrayList<Expense> getExpenseByUserId(Long userId) {
    //     User user = userRepository.findById(userId).orElse(null);
    //     ArrayList<Expense> expenses = user.getExpenses();
    //     return expenses;
    // }
}

