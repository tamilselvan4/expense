package com.project.expense.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.expense.dto.CreateUserDto;
import com.project.expense.entity.User;
import com.project.expense.entity.UserInfoDetails;
import com.project.expense.repository.CompanyRepository;
import com.project.expense.repository.RoleRepository;
import com.project.expense.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
	private PasswordEncoder encoder;

    public User createUser(CreateUserDto createUser) {
        User u = new User();
        u.setUserName(createUser.getUserName());
        u.setEmail(createUser.getEmail());
        u.setPassword(encoder.encode(createUser.getPassword()) );
        u.setCompany(companyRepository.findById(createUser.getCompanyId()).orElseThrow());
        u.setUserRole(roleRepository.findById(createUser.getUserRole()).orElseThrow());
        u.setIsActive(createUser.getIsActive());
        u.setAdminId(userRepository.findById(createUser.getAdminId()).orElseThrow());

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
            existingUser.setCompany(companyRepository.findById(updatedUser.getCompanyId()).orElseThrow());
            existingUser.setUserRole(roleRepository.findById(updatedUser.getUserRole()).orElseThrow());

            return userRepository.saveAndFlush(existingUser);
        }
        else {
            return null;
        }
    }

    public boolean existsUser(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByEmail(email); 

		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found "));
    }

}

