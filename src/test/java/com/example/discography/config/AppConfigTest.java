package com.example.discography.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.discography.repository.ArtistRepository;
import com.example.discography.repository.TrackRepository;
import com.example.discography.service.ArtistService;
import com.example.discography.service.TrackService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AppConfigTest {

    @Test
    void configuresSingletonBeansAndInitializesTheDiscography() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ArtistRepository artistRepository = context.getBean(ArtistRepository.class);
            TrackRepository trackRepository = context.getBean(TrackRepository.class);

            assertEquals(10, artistRepository.findAll().size());
            assertEquals(50, trackRepository.findAll().size());
            assertSame(artistRepository, context.getBean(ArtistRepository.class));
            assertSame(trackRepository, context.getBean(TrackRepository.class));
            assertSame(context.getBean(ArtistService.class), context.getBean(ArtistService.class));
            assertSame(context.getBean(TrackService.class), context.getBean(TrackService.class));
        }
    }
}
