package com.example.discography.service;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;
import com.example.discography.repository.ArtistRepository;
import com.example.discography.repository.TrackRepository;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TrackService {

    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;

    public TrackService(TrackRepository trackRepository, ArtistRepository artistRepository) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }

    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    public Track create(String title, String genre, String duration, String albumTitle, Set<Integer> artistIds) {
        validateText(title, "Track title");
        validateText(genre, "Track genre");
        validateText(duration, "Track duration");
        validateText(albumTitle, "Album title");
        validateArtists(artistIds);

        Track track = new Track(nextTrackId(), title.trim(), genre.trim(), duration.trim(), albumTitle.trim());
        for (Integer artistId : new LinkedHashSet<>(artistIds)) {
            Artist artist = artistRepository.findById(artistId);
            track.addArtistId(artistId);
            artist.addTrackId(track.getId());
        }
        return trackRepository.save(track);
    }

    public boolean deleteById(Integer id) {
        Track track = trackRepository.deleteById(id);
        if (track == null) {
            return false;
        }

        for (Integer artistId : track.getArtistIds()) {
            Artist artist = artistRepository.findById(artistId);
            if (artist != null) {
                artist.removeTrackId(id);
            }
        }
        return true;
    }

    private int nextTrackId() {
        int nextId = 1;
        for (Track track : trackRepository.findAll()) {
            if (track.getId() >= nextId) {
                nextId = track.getId() + 1;
            }
        }
        return nextId;
    }

    private void validateArtists(Set<Integer> artistIds) {
        if (artistIds == null || artistIds.isEmpty()) {
            throw new IllegalArgumentException("At least one artist is required.");
        }
        for (Integer artistId : artistIds) {
            if (artistId == null || !artistRepository.existsById(artistId)) {
                throw new IllegalArgumentException("Every selected artist must exist.");
            }
        }
    }

    private void validateText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
    }
}
