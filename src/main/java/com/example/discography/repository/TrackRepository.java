package com.example.discography.repository;

import com.example.discography.model.Track;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrackRepository {

    private final Map<Integer, Track> tracks = new LinkedHashMap<>();

    public List<Track> findAll() {
        return new ArrayList<>(tracks.values());
    }

    public Track findById(Integer id) {
        return tracks.get(id);
    }

    public Track save(Track track) {
        tracks.put(track.getId(), track);
        return track;
    }

    public Track deleteById(Integer id) {
        return tracks.remove(id);
    }

    public boolean existsById(Integer id) {
        return tracks.containsKey(id);
    }
}
