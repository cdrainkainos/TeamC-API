package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private int id;
    private String email;
    private String password;
    private String role;

    public User() {

    }

    public User(@JsonProperty("id") int id,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password,
                @JsonProperty("role") String role){
        this.setId(id);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() { return role; }

    public void setRole(String role) {
        this.role = role;
    }
}
