package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Capability {

    private int id;
    private String capabilityName;

    @JsonCreator
    public Capability(
            @JsonProperty("id") int id,
            @JsonProperty("capability_name") String capabilityName) {
        this.setId(id);
        this.setCapabilityName(capabilityName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
