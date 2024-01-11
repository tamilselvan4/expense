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

import com.project.expense.dto.CreateUserDto;
import com.project.expense.entity.Expense;
import com.project.expense.entity.User;
import com.project.expense.service.ExpenseService;
import com.project.expense.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/newuser")
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
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam String password) {
        boolean valid = userService.login(email, password);
        if(valid) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        boolean deleted = userService.deleteUser(userId);

        if (deleted) {
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("User not found or unable to delete", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{userId}/expense")
    public ResponseEntity<List<Expense>> getAllExpensesForUser(@PathVariable("userId") Long userId) {
        List<Expense> userExpenses = expenseService.getAllExpensesForUser(userId);

        if (!userExpenses.isEmpty()) {
            return new ResponseEntity<>(userExpenses, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{userId}/expense/{stateId}")
    public ResponseEntity<List<Expense>> getExpenseByStateId(
        @PathVariable("userId") Long userId, 
        @PathVariable("stateId") Long stateId) {

        List<Expense> userExpensesById = expenseService.getAllExpensesForUser(userId);
        List<Expense> userExpensesByState = expenseService.getAllExpensesByState(stateId);

        List<Expense> commonExpenses = new ArrayList<>(userExpensesById);
        commonExpenses.retainAll(userExpensesByState);

        if(!commonExpenses.isEmpty()){
            return new ResponseEntity<>(commonExpenses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
}

