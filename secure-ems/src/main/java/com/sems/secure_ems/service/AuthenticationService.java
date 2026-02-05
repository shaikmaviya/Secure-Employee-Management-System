package com.sems.secure_ems.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sems.secure_ems.entity.User;
import com.sems.secure_ems.repository.UserRepository;

@Service
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Optional<User> authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        
        if (user.isPresent() && user.get().getIsActive()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return user;
            }
        }
        return Optional.empty();
    }
}
