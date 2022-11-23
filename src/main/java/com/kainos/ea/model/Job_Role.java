package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Job_Role {



    private int id;
    private String role_title;


    @JsonCreator
    public Job_Role(
            @JsonProperty("id") int id,
            @JsonProperty("Kainos_Job_Title") String role_title) {
        this.setRoleID(id);
        this.setRole_title(role_title);
    }

    //currently not used
    public int getRoleID() {
        return id;
    }

    public void setRoleID(int roleID) {
        this.id = roleID;
    }

    //currently not used
    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }


}