package com.example.discography.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;
import com.example.discography.repository.ArtistRepository;
import com.example.discography.repository.TrackRepository;
import com.example.discography.service.ArtistDetails;
import com.example.discography.service.ArtistService;
import com.example.discography.service.TrackService;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class XmlConfigTest {

    @Test
    void configuresSingletonBeansAndInitializesTheDiscography() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            ArtistRepository artistRepository = context.getBean(ArtistRepository.class);
            TrackRepository trackRepository = context.getBean(TrackRepository.class);
            ArtistService artistService = context.getBean(ArtistService.class);
            TrackService trackService = context.getBean(TrackService.class);

            assertEquals(10, artistRepository.findAll().size());
            assertEquals(50, trackRepository.findAll().size());
            assertEquals(10, artistService.findAll().size());
            assertEquals(50, trackService.findAll().size());
            assertSame(artistRepository, context.getBean(ArtistRepository.class));
            assertSame(trackRepository, context.getBean(TrackRepository.class));
            assertSame(artistService, context.getBean(ArtistService.class));
            assertSame(trackService, context.getBean(TrackService.class));

            for (Artist artist : artistService.findAll()) {
                ArtistDetails details = artistService.findByNameWithTracks(artist.getName());
                assertNotNull(details);
                assertEquals(5, details.tracks().size());
                for (Track track : details.tracks()) {
                    assertSame(trackRepository.findById(track.getId()), track);
                    assertEquals(Set.of(artist.getId()), track.getArtistIds());
                }
            }
        }
    }

    @Test
    void injectsSharedRepositoriesIntoBothServices() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            ArtistRepository artistRepository = context.getBean(ArtistRepository.class);
            TrackRepository trackRepository = context.getBean(TrackRepository.class);
            ArtistService artistService = context.getBean(ArtistService.class);
            TrackService trackService = context.getBean(TrackService.class);

            Artist artist = artistService.create("XML Artist", "Colombian");
            Track track = trackService.create("XML Collaboration", "Pop", "3:30", "XML Album",
                    Set.of(artist.getId(), 1));

            assertEquals(11, artist.getId());
            assertEquals(51, track.getId());
            assertSame(artist, artistRepository.findById(artist.getId()));
            assertSame(track, trackRepository.findById(track.getId()));
            assertEquals(Set.of(artist.getId(), 1), track.getArtistIds());
            assertEquals(1, artistService.findByNameWithTracks("xml artist").tracks().size());
            assertEquals(6, artistService.findByNameWithTracks("Adele").tracks().size());

            assertTrue(artistService.deleteById(artist.getId()));
            assertFalse(artistRepository.existsById(artist.getId()));
            assertEquals(Set.of(1), track.getArtistIds());
            assertTrue(trackService.deleteById(track.getId()));
            assertFalse(trackRepository.existsById(track.getId()));
            assertEquals(5, artistService.findByNameWithTracks("Adele").tracks().size());
        }
    }
}
