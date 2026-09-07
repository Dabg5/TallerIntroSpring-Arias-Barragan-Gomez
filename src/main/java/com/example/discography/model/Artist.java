package com.example.discography.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Artist {

    private Integer id;
    private String name;
    private String nationality;
    private final Set<Integer> trackIds = new LinkedHashSet<>();

    public Artist(Integer id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
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

    public Set<Integer> getTrackIds() {
        return new LinkedHashSet<>(trackIds);
    }

    public void addTrackId(Integer trackId) {
        trackIds.add(trackId);
    }

    public void removeTrackId(Integer trackId) {
        trackIds.remove(trackId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Artist artist)) {
            return false;
        }
        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
