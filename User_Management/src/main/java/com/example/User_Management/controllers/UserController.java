package com.example.User_Management.controllers;

import com.example.User_Management.entities.User;
import com.example.User_Management.dtos.UserDTO;
import com.example.User_Management.repositories.UserRepository;
import com.example.User_Management.services.UserService;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-users-dto")
    public ResponseEntity<List<UserDTO>> getAllUsersDTO() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setRole(user.getRole());

            usersDTO.add(userDTO);
        }
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/user-exists/{id}")
    public boolean userExists(@PathVariable Long id) {
        return userService.existsById(id);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
//        String username = request.get("username");
//        String password = request.get("password");
//        Map<String, Object> response = new HashMap<>();
//
//        if (userService.authenticateUser(username, password)) {
//            response.put("message", "Autentificare reusita!");
//
//            String role = userService.getUserRoleByUsername(username);
//            response.put("role", role);
//
//            Long userId = userService.getUserIdByName(username);
//            response.put("userId", userId);
//
//            return ResponseEntity.ok(response);
//        } else {
//            response.put("message", "Autentificare esuata.");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        userService.registerUser(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        try {
            return ResponseEntity.ok("User registered successfully and synced with Device Service.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User registered but failed to sync with Device Service.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok("User updated successfully");
    }
}
