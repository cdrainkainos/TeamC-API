package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CompetencyPerBandLvl {
    private int bandLvl;
    private String bandName;
    private String competencyName;

    @JsonCreator
    public CompetencyPerBandLvl(
            @JsonProperty("band_level") int bandLvl,
            @JsonProperty("band_name") String bandName,
            @JsonProperty("competency_name") String competencyName){
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

    public String getCompetencyName() {
        return competencyName;
    }

    public void setCompetencyName(String competencyName) {
        this.competencyName = competencyName;
    }
}
