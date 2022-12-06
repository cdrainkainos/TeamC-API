package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobFamily {

    private int id;
    private int capabilityId;
    private String jobFamily;

    @JsonCreator
    public JobFamily(
            @JsonProperty("id") int id,
            @JsonProperty("capability_id") int capabilityId,
            @JsonProperty("job_family") String jobFamily) {
        this.setId(id);
        this.setCapabilityId(capabilityId);
        this.setJobFamily(jobFamily);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(int capabilityId) {
        this.capabilityId = capabilityId;
    }

    public String getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
    }
}
