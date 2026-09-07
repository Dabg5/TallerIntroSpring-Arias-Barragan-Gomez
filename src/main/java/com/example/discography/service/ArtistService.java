package com.example.discography.service;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;
import com.example.discography.repository.ArtistRepository;
import com.example.discography.repository.TrackRepository;
import java.util.ArrayList;
import java.util.List;

public class ArtistService {

    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;

    public ArtistService(ArtistRepository artistRepository, TrackRepository trackRepository) {
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist create(String name, String nationality) {
        validateText(name, "Artist name");
        validateText(nationality, "Artist nationality");

        Artist artist = new Artist(nextArtistId(), name.trim(), nationality.trim());
        return artistRepository.save(artist);
    }

    public ArtistDetails findByNameWithTracks(String name) {
        validateText(name, "Artist name");
        Artist artist = artistRepository.findByName(name.trim());
        if (artist == null) {
            return null;
        }
        return new ArtistDetails(artist, findTracksByArtistId(artist.getId()));
    }

    public List<Track> findTracksByArtistId(Integer artistId) {
        List<Track> tracks = new ArrayList<>();
        for (Integer trackId : artistRepository.findById(artistId).getTrackIds()) {
            Track track = trackRepository.findById(trackId);
            if (track != null) {
                tracks.add(track);
            }
        }
        return tracks;
    }

    public boolean deleteById(Integer id) {
        Artist artist = artistRepository.deleteById(id);
        if (artist == null) {
            return false;
        }

        for (Integer trackId : artist.getTrackIds()) {
            Track track = trackRepository.findById(trackId);
            if (track != null) {
                track.removeArtistId(id);
            }
        }
        return true;
    }

    private int nextArtistId() {
        int nextId = 1;
        for (Artist artist : artistRepository.findAll()) {
            if (artist.getId() >= nextId) {
                nextId = artist.getId() + 1;
            }
        }
        return nextId;
    }

    private void validateText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
    }
}
