package com.example.repository;

import com.example.model.Track;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class TrackRepository {
    private Collection<Track> tracks;

    public TrackRepository() {
        tracks = new ArrayList<>();
    }

    public Collection<Track> findAll() {
        return tracks;
    }

    public Track findById(Integer id) {

        for (Track track : tracks) {

            if (track.getId().equals(id)) {
                return track;
            }
        }

        return null;
    }

    public void save(Track track) {
        tracks.add(track);
    }

    public void delete(Integer id) {

        tracks.removeIf(
                track -> track.getId().equals(id)
        );
    }
}
