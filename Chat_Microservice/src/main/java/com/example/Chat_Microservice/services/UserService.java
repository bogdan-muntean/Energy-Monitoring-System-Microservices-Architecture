package com.example.Chat_Microservice.services;

import com.example.Chat_Microservice.entities.UserDTO;
import com.example.Chat_Microservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    String USERS_API_URL = "http://localhost:8080/users";

    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate = null;

    public UserService() { this.restTemplate = new RestTemplate(); }

//    public List<User> getAllUsers() {
//        return (List<User>) userRepository.findAll();
//    }

    public List<UserDTO> getAllUsers() {
        String direction = USERS_API_URL + "/get-all";
        List<UserDTO> userDTOS = restTemplate.getForObject(direction, List.class);
//        List<User> usersDTO = new ArrayList<>();
//        for (User user : users) {
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(user.getId());
//            userDTO.setUsername(user.getUsername());
//            userDTO.setRole(user.getRole());
//
//            usersDTO.add(userDTO);
//        }
//        if (user != null) {
//            return user[0];
//        } else {
//            System.out.println("No users found");
//            return null;
//        }
        return userDTOS;
    }

//    public User getUser(int userId) {
//        String direction = USERS_API_URL + "/" + userId;
//        User[] user = restTemplate.getForObject(direction, User[].class);
//
//        if (user != null) {
//            return user[0];
//        } else {
//            System.out.println("No users found");
//            return null;
//        }
//    }
//
//    public String getUserUsername(Integer userId) {
//        String direction = USERS_API_URL + "/" + userId;
//        User user = restTemplate.getForObject(direction, User.class);
//
//        if (user != null) {
//            return user.getUsername();
//        } else {
//            System.out.println("No users found");
//            return null;
//        }
//    }
//
//    private void syncUser(User user) { this.userRepository.save(user); }

//    public void syncAllUsers(String token) {
//        String direction = USERS_API_URL + "/get-users-dto";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + token);
////        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibWFyaWFfaW9uZXNjdSIsInN1YiI6Im1hcmlhX2lvbmVzY3UiLCJpYXQiOjE3MzYzNzk4ODcsImV4cCI6MTczNjM4MzQ4N30.1TZtnl8sZDysxfO-CZ7nNiJE-KrpJliF2LEtaWu6HK4");
//
//        // Create an HTTP entity with headers
//        HttpEntity entity = new HttpEntity<>(null, headers);
//
//        User[] users = restTemplate.exchange(direction, HttpMethod.GET,entity,User[].class).getBody();
//
//        if (users != null) {
//            for (User user : users) {
//                try {
//                    userRepository.save(user);
//                } catch (Exception e) {
//                    System.out.println("Failed to add user " + user.getId());
//                }
//            }
//        } else {
//            System.out.println("No users found");
//        }
//    }
}
