package com.example.discography.repository;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;

public class DiscographyDataInitializer {

    private static final String[][] ARTISTS = {
            {"Adele", "British"},
            {"Coldplay", "British"},
            {"Shakira", "Colombian"},
            {"Daft Punk", "French"},
            {"Beyonce", "American"},
            {"Soda Stereo", "Argentine"},
            {"The Weeknd", "Canadian"},
            {"Rosalia", "Spanish"},
            {"Imagine Dragons", "American"},
            {"Juanes", "Colombian"}
    };

    private static final String[][] TRACKS = {
            {"Hello", "Pop", "4:55", "25"},
            {"Rolling in the Deep", "Pop", "3:48", "21"},
            {"Someone Like You", "Pop", "4:45", "21"},
            {"Easy on Me", "Pop", "3:44", "30"},
            {"Skyfall", "Pop", "4:46", "Skyfall"},
            {"Yellow", "Alternative Rock", "4:27", "Parachutes"},
            {"Fix You", "Alternative Rock", "4:55", "X&Y"},
            {"Viva la Vida", "Alternative Rock", "4:02", "Viva la Vida"},
            {"The Scientist", "Alternative Rock", "5:09", "A Rush of Blood to the Head"},
            {"Adventure of a Lifetime", "Pop Rock", "4:23", "A Head Full of Dreams"},
            {"Hips Don't Lie", "Latin Pop", "3:38", "Oral Fixation Vol. 2"},
            {"Waka Waka", "Latin Pop", "3:22", "Sale el Sol"},
            {"Whenever Wherever", "Latin Pop", "3:16", "Laundry Service"},
            {"La Tortura", "Latin Pop", "3:32", "Fijacion Oral Vol. 1"},
            {"Ojos Asi", "Latin Pop", "3:57", "Donde Estan los Ladrones"},
            {"One More Time", "Electronic", "5:20", "Discovery"},
            {"Get Lucky", "Electronic", "6:09", "Random Access Memories"},
            {"Around the World", "Electronic", "7:09", "Homework"},
            {"Harder Better Faster Stronger", "Electronic", "3:44", "Discovery"},
            {"Instant Crush", "Electronic", "5:37", "Random Access Memories"},
            {"Halo", "R&B", "4:21", "I Am... Sasha Fierce"},
            {"Single Ladies", "R&B", "3:13", "I Am... Sasha Fierce"},
            {"Crazy in Love", "R&B", "3:56", "Dangerously in Love"},
            {"Run the World", "R&B", "3:56", "4"},
            {"Irreplaceable", "R&B", "3:47", "B'Day"},
            {"De Musica Ligera", "Rock", "3:33", "Cancion Animal"},
            {"Persiana Americana", "Rock", "4:53", "Signos"},
            {"En la Ciudad de la Furia", "Rock", "5:47", "Doble Vida"},
            {"Cuando Pase el Temblor", "Rock", "3:46", "Nada Personal"},
            {"Tratame Suavemente", "Rock", "3:21", "Soda Stereo"},
            {"Blinding Lights", "R&B", "3:20", "After Hours"},
            {"Save Your Tears", "R&B", "3:35", "After Hours"},
            {"Starboy", "R&B", "3:50", "Starboy"},
            {"The Hills", "R&B", "4:02", "Beauty Behind the Madness"},
            {"Can't Feel My Face", "R&B", "3:35", "Beauty Behind the Madness"},
            {"Malamente", "Flamenco Pop", "2:30", "El Mal Querer"},
            {"Con Altura", "Latin Pop", "2:41", "Con Altura"},
            {"Despecha", "Merengue", "2:37", "Motomami"},
            {"Pienso en Tu Mira", "Flamenco Pop", "3:13", "El Mal Querer"},
            {"Beso", "Latin Pop", "3:14", "RR"},
            {"Believer", "Alternative Rock", "3:24", "Evolve"},
            {"Demons", "Alternative Rock", "2:57", "Night Visions"},
            {"Radioactive", "Alternative Rock", "3:06", "Night Visions"},
            {"Thunder", "Alternative Rock", "3:07", "Evolve"},
            {"Whatever It Takes", "Alternative Rock", "3:21", "Evolve"},
            {"La Camisa Negra", "Latin Rock", "3:36", "Mi Sangre"},
            {"A Dios le Pido", "Latin Rock", "3:27", "Un Dia Normal"},
            {"Me Enamora", "Latin Pop", "3:12", "La Vida... Es un Ratico"},
            {"Es Por Ti", "Latin Rock", "3:30", "Un Dia Normal"},
            {"Fotografia", "Latin Pop", "3:49", "Un Dia Normal"}
    };

    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;

    public DiscographyDataInitializer(ArtistRepository artistRepository, TrackRepository trackRepository) {
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }

    public void initialize() {
        if (!artistRepository.findAll().isEmpty() || !trackRepository.findAll().isEmpty()) {
            return;
        }

        for (int artistIndex = 0; artistIndex < ARTISTS.length; artistIndex++) {
            int artistId = artistIndex + 1;
            Artist artist = new Artist(artistId, ARTISTS[artistIndex][0], ARTISTS[artistIndex][1]);
            artistRepository.save(artist);

            for (int trackOffset = 0; trackOffset < 5; trackOffset++) {
                int trackIndex = artistIndex * 5 + trackOffset;
                int trackId = trackIndex + 1;
                String[] trackData = TRACKS[trackIndex];
                Track track = new Track(trackId, trackData[0], trackData[1], trackData[2], trackData[3]);
                track.addArtistId(artistId);
                artist.addTrackId(trackId);
                trackRepository.save(track);
            }
        }
    }
}
