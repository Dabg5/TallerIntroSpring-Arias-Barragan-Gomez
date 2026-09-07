package com.example.discography.repository;

import com.example.discography.model.Artist;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArtistRepository {

    private final Map<Integer, Artist> artists = new LinkedHashMap<>();

    public List<Artist> findAll() {
        return new ArrayList<>(artists.values());
    }

    public Artist findById(Integer id) {
        return artists.get(id);
    }

    public Artist findByName(String name) {
        for (Artist artist : artists.values()) {
            if (artist.getName().equalsIgnoreCase(name)) {
                return artist;
            }
        }
        return null;
    }

    public Artist save(Artist artist) {
        artists.put(artist.getId(), artist);
        return artist;
    }

    public Artist deleteById(Integer id) {
        return artists.remove(id);
    }

    public boolean existsById(Integer id) {
        return artists.containsKey(id);
    }
}
