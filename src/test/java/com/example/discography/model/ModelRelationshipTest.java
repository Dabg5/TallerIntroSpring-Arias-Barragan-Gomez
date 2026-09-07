package com.example.discography.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class ModelRelationshipTest {

    @Test
    void artistAndTrackStoreRelationshipIdentifiers() {
        Artist artist = new Artist(1, "Adele", "British");
        Track track = new Track(1, "Hello", "Pop", "4:55", "25");

        artist.addTrackId(track.getId());
        track.addArtistId(artist.getId());

        assertEquals(1, artist.getTrackIds().size());
        assertEquals(1, track.getArtistIds().size());

        artist.getTrackIds().clear();
        track.getArtistIds().clear();

        assertFalse(artist.getTrackIds().isEmpty());
        assertFalse(track.getArtistIds().isEmpty());
    }
}
