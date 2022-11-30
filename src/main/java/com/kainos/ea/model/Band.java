package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Band {

    private int id;
    private int bandLevel;
    private String bandName;

    @JsonCreator
    public Band(
            @JsonProperty("id") int id,
            @JsonProperty("band_level") int bandLevel,
            @JsonProperty("band_name") String bandName) {
        this.setId(id);
        this.setBandLevel(bandLevel);
        this.setBandName(bandName);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBandLevel() {
        return bandLevel;
    }
    public void setBandLevel(int bandLevel) {
        this.bandLevel = bandLevel;
    }
    public String getBandName() {
        return bandName;
    }
    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
