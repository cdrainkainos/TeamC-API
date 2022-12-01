package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSpecification {

    private String kainosJobTitle;
    private String jobSpecification;
    private String jobSpecificationLink;

    @JsonCreator
    public JobSpecification(
            @JsonProperty("kainos_job_title") String kainosJobTitle,
            @JsonProperty("job_specification") String jobSpecification,
            @JsonProperty("job_spec_link") String jobSpecificationLink) {
        this.setKainosJobTitle(kainosJobTitle);
        this.setJobSpecification(jobSpecification);
        this.setJobSpecificationLink(jobSpecificationLink);
    }

    public String getKainosJobTitle() {
        return kainosJobTitle;
    }

    public void setKainosJobTitle(String kainosJobTitle) {
        this.kainosJobTitle = kainosJobTitle;
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
