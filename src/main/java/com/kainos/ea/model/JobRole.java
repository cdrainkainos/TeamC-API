package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {

    private int id;
    private String role_title;

    private String bandName;

    @JsonCreator
    public JobRole(
            @JsonProperty("id") int id,
            @JsonProperty("kainos_job_title") String role_title,
            @JsonProperty("band_name") String bandName) {
        this.setRoleID(id);
        this.setRole_title(role_title);
        this.setBandName(bandName);
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

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
