package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {

    private int id;
    private String role_title;

    @JsonCreator
    public JobRole(
            @JsonProperty("id") int id,
            @JsonProperty("Kainos_Job_Title") String role_title) {
        this.setRoleID(id);
        this.setRole_title(role_title);
    }

    public int getRoleID() {
        return id;
    }

    public void setRoleID(int roleID) {
        this.id = roleID;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

}
