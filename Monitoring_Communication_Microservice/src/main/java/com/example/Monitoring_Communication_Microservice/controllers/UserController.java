//package com.example.Monitoring_Communication_Microservice.controllers;
//
//import com.example.Monitoring_Communication_Microservice.entities.User;
//import com.example.Monitoring_Communication_Microservice.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//@CrossOrigin
//public class UserController {
//    @Autowired
//    private UserService userService;
//
////    @Autowired
////    private RestTemplate restTemplate;
//
//    @GetMapping("/get-all")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @PostMapping("/syncUsers")
//    public ResponseEntity<String> syncUsers() {
//        userService.syncAllUsers();
//        return ResponseEntity.ok("Succesful syncronization of users in chat-db");
//    }
//}
