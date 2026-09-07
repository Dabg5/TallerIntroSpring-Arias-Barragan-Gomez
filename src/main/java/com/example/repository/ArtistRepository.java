package com.example.repository;

import com.example.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class ArtistRepository {
    private Collection<Artist> artists;

    public ArtistRepository() {
        artists = new ArrayList<>();
    }

    public Collection<Artist> findAll() {
        return artists;
    }

    public Artist findById(Integer id) {

        for (Artist artist : artists) {

            if (artist.getId().equals(id)) {
                return artist;
            }
        }

        return null;
    }

    public Artist findByName(String name) {

        for (Artist artist : artists) {

            if (artist.getName().equalsIgnoreCase(name)) {
                return artist;
            }
        }

        return null;
    }

    public void save(Artist artist) {
        artists.add(artist);
    }

    public void delete(Integer id) {

        artists.removeIf(
                artist -> artist.getId().equals(id)
        );
    }
}
