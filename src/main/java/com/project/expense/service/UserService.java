package com.project.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.expense.dto.CreateUserRequest;
import com.project.expense.entity.User;
import com.project.expense.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest createUserRequest) {
        User newUser = convertToUserEntity(createUserRequest);
        return userRepository.save(newUser);
    }

    private User convertToUserEntity(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setUserName(createUserRequest.getUserName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setCompany(createUserRequest.getCompanyId());
        return user;
    }

    public String validateUser(User user) {
        String email = user.getEmail();
        if(email==null) {
            return "false";
        }
        else {
            User u = userRepository.findByEmail(email);

        }
        return "false";
    }
}

