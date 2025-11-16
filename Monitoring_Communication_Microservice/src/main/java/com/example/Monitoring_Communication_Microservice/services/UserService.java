//package com.example.Monitoring_Communication_Microservice.services;
//
//import com.example.Monitoring_Communication_Microservice.dto.DeviceDTO;
//import com.example.Monitoring_Communication_Microservice.entities.DeviceHourlyLimit;
//import com.example.Monitoring_Communication_Microservice.entities.User;
//import com.example.Monitoring_Communication_Microservice.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Service
//public class UserService {
//    String USERS_API_URL = "http://localhost:8080/users";
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private RestTemplate restTemplate = null;
//
//    public UserService() { this.restTemplate = new RestTemplate(); }
//
//    public List<User> getAllUsers() {
//        return (List<User>) userRepository.findAll();
//    }
//
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
//
//    public void syncAllUsers() {
//        String direction = USERS_API_URL + "/get-users-dto";
//        User[] users = restTemplate.getForObject(direction, User[].class);
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
//}
