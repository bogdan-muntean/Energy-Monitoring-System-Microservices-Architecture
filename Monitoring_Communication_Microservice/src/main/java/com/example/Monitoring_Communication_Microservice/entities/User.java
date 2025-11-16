//package com.example.Monitoring_Communication_Microservice.entities;
//import jakarta.persistence.*;
//
//import java.io.Serial;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "users")
//public class User implements Serializable {
//    @Id
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Column(name = "username", nullable = false)
//    private String username;
//
//    @Column(name = "role", nullable = false)     // Administrator / Client
//    private String role;
//
//    public User() {
//    }
//
//    public User(Long id, String username, String role) {
//        this.id = id;
//        this.username = username;
//        this.role = role;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//}