package com.example.service;

import com.example.model.Artist;
import com.example.model.Track;
import com.example.repository.ArtistRepository;
import com.example.repository.TrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ArtistService {
    private ArtistRepository artistRepository;
    private TrackRepository trackRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository,
                         TrackRepository trackRepository) {

        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }

    public Iterable<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public void createArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public Artist findArtistByName(String name) {
        return artistRepository.findByName(name);
    }

    public void deleteArtist(Integer id) {

        Artist artist = artistRepository.findById(id);

        if (artist == null) {
            return;
        }

        for (Track track : trackRepository.findAll()) {
            track.getArtists().remove(artist);
        }

        artistRepository.delete(id);
    }
}
