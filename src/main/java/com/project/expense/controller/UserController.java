package com.project.expense.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.expense.dto.CreateUserRequest;
import com.project.expense.entity.User;
import com.project.expense.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User newUser = userService.createUser(createUserRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}

