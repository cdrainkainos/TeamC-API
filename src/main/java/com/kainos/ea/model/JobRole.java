package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {

    private int id;
    private String role_title;

    private String capability_name;

    @JsonCreator
    public JobRole(
            @JsonProperty("id") int id,
            @JsonProperty("Kainos_Job_Title") String role_title,
            @JsonProperty("capability_name") String capability) {
                this.setRoleID(id);
                this.setRole_title(role_title);
                this.setCapability_name(capability);

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

    public String getCapability_name() { return capability_name; }

    public void setCapability_name(String capability_name) { this.capability_name = capability_name; }

}
