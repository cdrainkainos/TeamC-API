package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BandCompetencies {
    private int bandLvl;
    private String bandName;
    private List<String> competencyName;


    @JsonCreator
    public BandCompetencies(
            @JsonProperty("band_level") int bandLvl,
            @JsonProperty("band_name") String bandName,
            @JsonProperty("competency_name") List<String> competencyName){
        this.setBandLvl(bandLvl);
        this.setBandName(bandName);
        this.setCompetencyName(competencyName);
    }

    public int getBandLvl() {
        return bandLvl;
    }

    public void setBandLvl(int bandLvl) {
        this.bandLvl = bandLvl;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public List<String> getCompetencyName() {
        return competencyName;
    }

    public void setCompetencyName(List<String> competencyName) {
        this.competencyName = competencyName;
    }
}
