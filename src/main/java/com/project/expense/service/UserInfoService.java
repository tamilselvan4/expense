package com.project.expense.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import com.project.expense.entity.User;
import com.project.expense.entity.UserInfoDetails;
import com.project.expense.repository.UserRepository;

import java.util.Optional; 

@Service
public class UserInfoService implements UserDetailsService { 

	@Autowired
	private UserRepository repository; 

	@Autowired
	private PasswordEncoder encoder; 

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 

		Optional<User> userDetail = repository.findByEmail(email); 

		// Converting userDetail to UserDetails 
		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + email)); 
	} 

	public String addUser(User userInfo) { 
		userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
		repository.save(userInfo); 
		return "User Added Successfully"; 
	} 


} 

