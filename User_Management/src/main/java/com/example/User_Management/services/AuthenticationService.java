package com.example.User_Management.services;

import com.example.User_Management.entities.AuthenticationResponse;
import com.example.User_Management.entities.JwtUtil;
import com.example.User_Management.entities.User;
import com.example.User_Management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    private static final Set<String> tokenBlacklist = Collections.synchronizedSet(new HashSet<>());

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationResponse authenticate(String username, String password) {
        List<User> users = userRepository.findUserByUsername(username);
        // Ensure the user exists
        if (users.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = users.get(0);

        // Validate password
        if (user.isPresent() && password.equals(user.getPassword())) {
            return new AuthenticationResponse(jwtUtil.generateToken(username), user);
        }
        throw new RuntimeException("Invalid credentials");

//        if (user.isPresent() && bCryptPasswordEncoder.matches(password, user.getPassword())) {
//            return new AuthenticationResponse(jwtUtil.generateToken(username), user);
//        }
//        throw new RuntimeException("Invalid credentials");
//
    }

    public void logout(String token) {
        this.tokenBlacklist.add(token);
        System.out.println("Token " + token + " has been logged out and added to the blacklist.");
    }

    public static boolean  isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }
}
