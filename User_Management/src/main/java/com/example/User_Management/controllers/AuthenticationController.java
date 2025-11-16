package com.example.User_Management.controllers;

import com.example.User_Management.entities.AuthenticationRequest;
import com.example.User_Management.entities.AuthenticationResponse;
import com.example.User_Management.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest.getName(), authenticationRequest.getPassword());
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token) {
        authenticationService.logout(token);
        return "User logged out successfully";
    }
}
