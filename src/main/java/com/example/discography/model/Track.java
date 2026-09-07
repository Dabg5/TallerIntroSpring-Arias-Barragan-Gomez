package com.example.discography.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Track {

    private Integer id;
    private String title;
    private String genre;
    private String duration;
    private String albumTitle;
    private final Set<Integer> artistIds = new LinkedHashSet<>();

    public Track(Integer id, String title, String genre, String duration, String albumTitle) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.albumTitle = albumTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Set<Integer> getArtistIds() {
        return new LinkedHashSet<>(artistIds);
    }

    public void addArtistId(Integer artistId) {
        artistIds.add(artistId);
    }

    public void removeArtistId(Integer artistId) {
        artistIds.remove(artistId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Track track)) {
            return false;
        }
        return Objects.equals(id, track.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
