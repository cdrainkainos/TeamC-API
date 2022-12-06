package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {

    private int id;
    private int bandId;
    private int jobFamilyId;
    private String role_title;
    private String jobSpecification;
    private String jobSpecLink;

    @JsonCreator
    public JobRoleRequest(
            @JsonProperty("id") int id,
            @JsonProperty("band_id") int bandId,
            @JsonProperty("job_family_id") int jobFamilyId,
            @JsonProperty("kainos_job_title") String role_title,
            @JsonProperty("job_specification") String jobSpecification,
            @JsonProperty("job_spec_link") String jobSpecLink
    ) {
        this.setId(id);
        this.setRole_title(role_title);
        this.setBandId(bandId);
        this.setJobFamilyId(jobFamilyId);
        this.setJobSpecification(jobSpecification);
        this.setJobSpecLink(jobSpecLink);
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getJobFamilyId() {
        return jobFamilyId;
    }

    public void setJobFamilyId(int jobFamilyId) {
        this.jobFamilyId = jobFamilyId;
    }

    public String getJobSpecification() {
        return jobSpecification;
    }

    public void setJobSpecification(String jobSpecification) {
        this.jobSpecification = jobSpecification;
    }

    public String getJobSpecLink() {
        return jobSpecLink;
    }

    public void setJobSpecLink(String jobSpecLink) {
        this.jobSpecLink = jobSpecLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }
}
