package com.project.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.expense.dto.CreateUserRequest;
import com.project.expense.entity.User;
import com.project.expense.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/newuser")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User newUser = userService.createUser(createUserRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public User postMethodName(@RequestBody User user) {        
        return user;
    }
    
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String validate = userService.validateUser(user);    
        return validate;
    }
    
}

