package com.example.discography.config;

import com.example.discography.repository.ArtistRepository;
import com.example.discography.repository.DiscographyDataInitializer;
import com.example.discography.repository.TrackRepository;
import com.example.discography.service.ArtistService;
import com.example.discography.service.TrackService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TrackRepository trackRepository() {
        return new TrackRepository();
    }

    @Bean
    public DiscographyDataInitializer discographyDataInitializer() {
        return new DiscographyDataInitializer();
    }

    @Bean
    public ArtistRepository artistRepository(TrackRepository trackRepository,
                                             DiscographyDataInitializer discographyDataInitializer) {
        ArtistRepository artistRepository = new ArtistRepository();
        discographyDataInitializer.initialize(artistRepository, trackRepository);
        return artistRepository;
    }

    @Bean
    public ArtistService artistService(ArtistRepository artistRepository, TrackRepository trackRepository) {
        return new ArtistService(artistRepository, trackRepository);
    }

    @Bean
    public TrackService trackService(TrackRepository trackRepository, ArtistRepository artistRepository) {
        return new TrackService(trackRepository, artistRepository);
    }
}
