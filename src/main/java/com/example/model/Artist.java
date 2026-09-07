package com.example.model;

import java.util.ArrayList;
import java.util.Collection;

public class Artist     {
    private Integer id;
    private String name;
    private String nationality;
    private Collection<Track> tracks;

    public Artist() {
        this.tracks = new ArrayList<>();
    }

    public Artist(Integer id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.tracks = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Collection<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }
}
