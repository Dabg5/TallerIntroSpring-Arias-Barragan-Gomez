package com.example.service;

import com.example.model.Artist;
import com.example.model.Track;
import com.example.repository.ArtistRepository;
import com.example.repository.TrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Service
public class TrackService {
    private TrackRepository trackRepository;
    private ArtistRepository artistRepository;

    @Autowired
    public TrackService(TrackRepository trackRepository,
                        ArtistRepository artistRepository) {

        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }

    public Iterable<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public void createTrack(Track track, Collection<Integer> artistIds) {

        for (Integer artistId : artistIds) {

            Artist artist = artistRepository.findById(artistId);

            if (artist != null) {

                track.getArtists().add(artist);
                artist.getTracks().add(track);
            }
        }

        trackRepository.save(track);
    }

    public void deleteTrack(Integer id) {

        Track track = trackRepository.findById(id);

        if (track == null) {
            return;
        }

        for (Artist artist : track.getArtists()) {
            artist.getTracks().remove(track);
        }

        trackRepository.delete(id);
    }
}
