package com.project.expense.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.project.expense.dto.CreateUserDto;
import com.project.expense.entity.Expense;
import com.project.expense.entity.User;
import com.project.expense.service.CategoryService;
import com.project.expense.service.ExpenseService;
import com.project.expense.service.JwtService;
import com.project.expense.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/new")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto createUser) {

        if(userService.isUserExists(createUser.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("user already exist");
        }

        User newUser = userService.createUser(createUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody CreateUserDto updatedUser) {
        User updateUser = userService.updateUser(userId, updatedUser);

        if(userService.existsUser(userId)){
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>( "user not found",HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String username, @RequestParam("password") String password) {
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        if(authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.generateToken(username), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
    
    @GetMapping("/{userId}/expense")
    public ResponseEntity<List<Expense>> getAllExpensesForUser(
        @PathVariable("userId") Long userId, 
        @RequestParam(required = false) Long statusId,
        @RequestParam(required = false) Long categoryId) {

        List<Expense> userExpenses = expenseService.getAllExpensesForUser(userId);

        if(categoryId != null && statusId != null) {

            List<Expense> userExpensesByStatus = expenseService.getAllExpensesByStatus(statusId);
            List<Expense> userExpensesByCategory = categoryService.getAllExpensesByCategory(categoryId);

            List<Expense> commonExpenses = new ArrayList<>(userExpenses);
            commonExpenses.retainAll(userExpensesByCategory);
            commonExpenses.retainAll(userExpensesByStatus);

            return new ResponseEntity<>(commonExpenses, HttpStatus.OK);
        }

        if(categoryId != null) {
            List<Expense> userExpensesByCategory = categoryService.getAllExpensesByCategory(categoryId);
            
            List<Expense> commonExpenses = new ArrayList<>(userExpenses);
            commonExpenses.retainAll(userExpensesByCategory);

            return new ResponseEntity<>(commonExpenses, HttpStatus.OK);
        }

        if (statusId != null) {
            List<Expense> userExpensesByStatus = expenseService.getAllExpensesByStatus(statusId);

            List<Expense> commonExpenses = new ArrayList<>(userExpenses);
            commonExpenses.retainAll(userExpensesByStatus);

            return new ResponseEntity<>(commonExpenses, HttpStatus.OK);
        }

        if (!userExpenses.isEmpty()) {
            return new ResponseEntity<>(userExpenses, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    
}

