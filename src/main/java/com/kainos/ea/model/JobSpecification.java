package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSpecification {

    private int id;
    private String jobSpecification;
    private String jobSpecificationLink;

    @JsonCreator
    public JobSpecification(
            @JsonProperty("id") int id,
            @JsonProperty("job_specification") String jobSpecification,
            @JsonProperty("job_spec_link") String jobSpecificationLink) {
        this.setId(id);
        this.setJobSpecification(jobSpecification);
        this.setJobSpecificationLink(jobSpecificationLink);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobSpecification() {
        return jobSpecification;
    }

    public void setJobSpecification(String jobSpecification) {
        this.jobSpecification = jobSpecification;
    }

    public String getJobSpecificationLink() {
        return jobSpecificationLink;
    }

    public void setJobSpecificationLink(String jobSpecificationLink) {
        this.jobSpecificationLink = jobSpecificationLink;
    }
}
