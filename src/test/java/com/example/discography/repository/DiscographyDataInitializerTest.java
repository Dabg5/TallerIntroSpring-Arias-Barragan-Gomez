package com.example.discography.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;
import org.junit.jupiter.api.Test;

class DiscographyDataInitializerTest {

    @Test
    void initializesTenArtistsAndFiftyTracksWithFiveTracksPerArtist() {
        ArtistRepository artistRepository = new ArtistRepository();
        TrackRepository trackRepository = new TrackRepository();

        new DiscographyDataInitializer(artistRepository, trackRepository).initialize();

        assertEquals(10, artistRepository.findAll().size());
        assertEquals(50, trackRepository.findAll().size());

        for (Artist artist : artistRepository.findAll()) {
            assertEquals(5, artist.getTrackIds().size());
            for (Integer trackId : artist.getTrackIds()) {
                Track track = trackRepository.findById(trackId);
                assertFalse(track == null);
                assertEquals(1, track.getArtistIds().size());
                assertEquals(artist.getId(), track.getArtistIds().iterator().next());
            }
        }
    }

    @Test
    void initializationDoesNotDuplicateExistingData() {
        ArtistRepository artistRepository = new ArtistRepository();
        TrackRepository trackRepository = new TrackRepository();
        DiscographyDataInitializer initializer = new DiscographyDataInitializer(artistRepository, trackRepository);

        initializer.initialize();
        initializer.initialize();

        assertEquals(10, artistRepository.findAll().size());
        assertEquals(50, trackRepository.findAll().size());
    }
}
