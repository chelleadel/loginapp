package com.app.loginapp.payload.request;

import java.util.Set;


public class SignupRequest {

    private String username;

    public SignupRequest(String username, String name, String role, String password) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    private String name;

    private String role;

    private String password;

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
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
