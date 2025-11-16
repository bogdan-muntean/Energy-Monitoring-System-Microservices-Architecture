package com.example.Chat_Microservice.entities;

import java.security.Principal;

public class CustomPrincipal implements Principal {
    private final String name;

    public CustomPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}