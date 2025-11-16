package com.example.User_Management.dtos;


//import org.springframework.hateoas.RepresentationModel;
//
//import org.jetbrains.annotations.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Entity
public class UserDTO extends RepresentationModel<UserDTO> {

    @Id
    private Long id;
//    @NotNull
    private String username;
//    @NotNull
    private String password;
//    @NotNull
    private String role;

    public UserDTO() {
    }

//    public UserDTO(Long id, String username, String password) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//    }

    public UserDTO(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO that = (UserDTO) o;
        return Objects.equals(role, that.getRole()) &&
                Objects.equals(username, that.getUsername()) &&
                Objects.equals(password, that.getPassword());
    }
}