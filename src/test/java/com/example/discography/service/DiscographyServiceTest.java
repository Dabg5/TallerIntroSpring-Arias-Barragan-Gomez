package com.example.discography.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;
import com.example.discography.repository.ArtistRepository;
import com.example.discography.repository.TrackRepository;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiscographyServiceTest {

    private ArtistRepository artistRepository;
    private TrackRepository trackRepository;
    private ArtistService artistService;
    private TrackService trackService;

    @BeforeEach
    void setUp() {
        artistRepository = new ArtistRepository();
        trackRepository = new TrackRepository();
        artistService = new ArtistService(artistRepository, trackRepository);
        trackService = new TrackService(trackRepository, artistRepository);
    }

    @Test
    void createsTrackWithSeveralArtistsAndKeepsBothSidesInSync() {
        Artist firstArtist = artistService.create("Artist One", "Colombian");
        Artist secondArtist = artistService.create("Artist Two", "Mexican");

        Track track = trackService.create("Shared Track", "Pop", "3:30", "Shared Album",
                Set.of(firstArtist.getId(), secondArtist.getId()));

        assertEquals(Set.of(firstArtist.getId(), secondArtist.getId()), track.getArtistIds());
        assertTrue(firstArtist.getTrackIds().contains(track.getId()));
        assertTrue(secondArtist.getTrackIds().contains(track.getId()));
    }

    @Test
    void findsArtistWithAllAssociatedTracks() {
        Artist artist = artistService.create("Artist One", "Colombian");
        trackService.create("First Track", "Pop", "3:30", "Album", Set.of(artist.getId()));
        trackService.create("Second Track", "Pop", "3:40", "Album", Set.of(artist.getId()));

        ArtistDetails details = artistService.findByNameWithTracks("artist one");

        assertNotNull(details);
        assertEquals(artist, details.artist());
        assertEquals(2, details.tracks().size());
    }

    @Test
    void deletesTrackAndRemovesItFromArtists() {
        Artist artist = artistService.create("Artist One", "Colombian");
        Track track = trackService.create("Track", "Pop", "3:30", "Album", Set.of(artist.getId()));

        assertTrue(trackService.deleteById(track.getId()));

        assertFalse(artist.getTrackIds().contains(track.getId()));
        assertFalse(trackRepository.existsById(track.getId()));
    }

    @Test
    void rejectsTrackWithoutArtists() {
        assertThrows(IllegalArgumentException.class,
                () -> trackService.create("Track", "Pop", "3:30", "Album", Set.of()));
    }
}
