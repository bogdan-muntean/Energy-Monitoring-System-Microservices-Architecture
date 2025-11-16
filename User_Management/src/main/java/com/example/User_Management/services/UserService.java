package com.example.User_Management.services;

import com.example.User_Management.entities.User;
import com.example.User_Management.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final View error;

    @Autowired
    public UserService(UserRepository userRepository, View error) {
        this.userRepository = userRepository;
        this.error = error;
    }

    @Autowired
    private RestTemplate restTemplate;

    // GET
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public List<User> getUserByName(String username){
        return userRepository.findUserByUsername(username);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public Long getUserIdByName(String username) {
        List<User> users = getUserByName(username);
        if (users.isEmpty()) {
            return null;
        }

        User user = users.get(0);
        return user.getId();
    }

    public String getUserRoleById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(User::getRole).orElse(null);
    }

    public String getUserRoleByUsername(String username) {
        List<User> users = getUserByName(username);
        if (users.isEmpty()) {
            return null;
        }

        User user = users.get(0);
        return user.getRole();
    }

    // POST, DELETE, UPDATE
    public void registerUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        String deleteDevicesUrl = "http://localhost:8081/devices/user/" + id + "/delete-all";

        try {
            ResponseEntity<Void> response = restTemplate.exchange(deleteDevicesUrl, HttpMethod.DELETE, null, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                LOGGER.info("Deleted all devices for user ID " + id);
            }
//            restTemplate.postForObject(deleteDevicesUrl, null, Void.class);
        } catch (Exception e) {
            LOGGER.error("Failed to delete devices for user ID " + id, e);
            throw new RuntimeException("Failed to delete devices for user ID " + id, e);
        }

        userRepository.deleteById(id);
        LOGGER.info("Deleted successfully all devices for user ID " + id + " user itself.");
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    // Autentificare
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        List<User> users = userRepository.findUserByUsername(name);
        if (users.isEmpty()) {
            throw new RuntimeException("User not found with name: " + name);
        }

        User user = users.get(0);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList()) // You can add roles or authorities here if you have them
                .build();
    }

//    public boolean authenticateUser(String username, String password) {
//        List<User> users = getUserByName(username);
//        if (users.isEmpty()) {
//            return false;
//        }
//
//        for(User user : users) {
//            if(user.getPassword().equals(password))
//                return true;
//        }
//
//        return false;
//    }
}