package com.example.config;

import com.example.model.Artist;
import com.example.model.Track;
import com.example.repository.ArtistRepository;
import com.example.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
    private ArtistRepository artistRepository;
    private TrackRepository trackRepository;

    @Autowired
    public DataInitializer(ArtistRepository artistRepository,
                           TrackRepository trackRepository) {

        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }

    @PostConstruct
    public void initialize() {

        // Create 10 artists

        Artist artist1 = new Artist(1, "Michael Jackson", "American");
        Artist artist2 = new Artist(2, "Taylor Swift", "American");
        Artist artist3 = new Artist(3, "The Weeknd", "Canadian");
        Artist artist4 = new Artist(4, "Adele", "British");
        Artist artist5 = new Artist(5, "Ed Sheeran", "British");
        Artist artist6 = new Artist(6, "Bruno Mars", "American");
        Artist artist7 = new Artist(7, "Dua Lipa", "British");
        Artist artist8 = new Artist(8, "Drake", "Canadian");
        Artist artist9 = new Artist(9, "Billie Eilish", "American");
        Artist artist10 = new Artist(10, "Shakira", "Colombian");

        artistRepository.save(artist1);
        artistRepository.save(artist2);
        artistRepository.save(artist3);
        artistRepository.save(artist4);
        artistRepository.save(artist5);
        artistRepository.save(artist6);
        artistRepository.save(artist7);
        artistRepository.save(artist8);
        artistRepository.save(artist9);
        artistRepository.save(artist10);

        // Create 50 tracks

        Track track1 = new Track(1, "Billie Jean", "Pop", "4:54", "Thriller");
        Track track2 = new Track(2, "Beat It", "Rock", "4:18", "Thriller");
        Track track3 = new Track(3, "Thriller", "Pop", "5:57", "Thriller");
        Track track4 = new Track(4, "Bad", "Pop", "4:07", "Bad");
        Track track5 = new Track(5, "Smooth Criminal", "Pop", "4:17", "Bad");

        Track track6 = new Track(6, "Love Story", "Country Pop", "3:55", "Fearless");
        Track track7 = new Track(7, "You Belong With Me", "Country Pop", "3:52", "Fearless");
        Track track8 = new Track(8, "Blank Space", "Pop", "3:51", "1989");
        Track track9 = new Track(9, "Shake It Off", "Pop", "3:39", "1989");
        Track track10 = new Track(10, "Anti-Hero", "Pop", "3:20", "Midnights");

        Track track11 = new Track(11, "Blinding Lights", "Synth Pop", "3:20", "After Hours");
        Track track12 = new Track(12, "Save Your Tears", "Synth Pop", "3:35", "After Hours");
        Track track13 = new Track(13, "Starboy", "R&B", "3:50", "Starboy");
        Track track14 = new Track(14, "The Hills", "R&B", "4:02", "Beauty Behind the Madness");
        Track track15 = new Track(15, "Can't Feel My Face", "Pop", "3:35", "Beauty Behind the Madness");

        Track track16 = new Track(16, "Hello", "Soul", "4:55", "25");
        Track track17 = new Track(17, "Rolling in the Deep", "Soul", "3:48", "21");
        Track track18 = new Track(18, "Someone Like You", "Pop", "4:45", "21");
        Track track19 = new Track(19, "Set Fire to the Rain", "Pop", "4:02", "21");
        Track track20 = new Track(20, "Easy on Me", "Pop", "3:44", "30");

        Track track21 = new Track(21, "Shape of You", "Pop", "3:53", "Divide");
        Track track22 = new Track(22, "Perfect", "Pop", "4:23", "Divide");
        Track track23 = new Track(23, "Thinking Out Loud", "Soul", "4:41", "X");
        Track track24 = new Track(24, "Photograph", "Pop", "4:18", "X");
        Track track25 = new Track(25, "Bad Habits", "Pop", "3:51", "=");

        Track track26 = new Track(26, "Just the Way You Are", "Pop", "3:40", "Doo-Wops & Hooligans");
        Track track27 = new Track(27, "Grenade", "Pop", "3:42", "Doo-Wops & Hooligans");
        Track track28 = new Track(28, "Locked Out of Heaven", "Pop Rock", "3:53", "Unorthodox Jukebox");
        Track track29 = new Track(29, "Uptown Funk", "Funk", "4:30", "Uptown Special");
        Track track30 = new Track(30, "24K Magic", "Funk", "3:46", "24K Magic");

        Track track31 = new Track(31, "New Rules", "Pop", "3:29", "Dua Lipa");
        Track track32 = new Track(32, "Levitating", "Pop", "3:23", "Future Nostalgia");
        Track track33 = new Track(33, "Don't Start Now", "Disco Pop", "3:03", "Future Nostalgia");
        Track track34 = new Track(34, "Physical", "Dance Pop", "3:13", "Future Nostalgia");
        Track track35 = new Track(35, "Houdini", "Dance Pop", "3:05", "Radical Optimism");

        Track track36 = new Track(36, "God's Plan", "Hip Hop", "3:18", "Scorpion");
        Track track37 = new Track(37, "Hotline Bling", "Hip Hop", "4:27", "Views");
        Track track38 = new Track(38, "One Dance", "Dancehall", "2:54", "Views");
        Track track39 = new Track(39, "In My Feelings", "Hip Hop", "3:37", "Scorpion");
        Track track40 = new Track(40, "Passionfruit", "R&B", "4:58", "More Life");

        Track track41 = new Track(41, "Bad Guy", "Electropop", "3:14", "When We All Fall Asleep");
        Track track42 = new Track(42, "Lovely", "Indie Pop", "3:20", "Don't Smile at Me");
        Track track43 = new Track(43, "Happier Than Ever", "Alternative", "4:58", "Happier Than Ever");
        Track track44 = new Track(44, "Ocean Eyes", "Indie Pop", "3:20", "Don't Smile at Me");
        Track track45 = new Track(45, "Therefore I Am", "Pop", "2:54", "Happier Than Ever");

        Track track46 = new Track(46, "Hips Don't Lie", "Latin Pop", "3:38", "Oral Fixation Vol. 2");
        Track track47 = new Track(47, "Waka Waka", "Latin Pop", "3:22", "Sale el Sol");
        Track track48 = new Track(48, "Whenever, Wherever", "Latin Pop", "3:16", "Laundry Service");
        Track track49 = new Track(49, "Chantaje", "Reggaeton", "3:16", "El Dorado");
        Track track50 = new Track(50, "La Tortura", "Latin Pop", "3:32", "Fijación Oral Vol. 1");

        // Add all tracks to the repository

        trackRepository.save(track1);
        trackRepository.save(track2);
        trackRepository.save(track3);
        trackRepository.save(track4);
        trackRepository.save(track5);

        trackRepository.save(track6);
        trackRepository.save(track7);
        trackRepository.save(track8);
        trackRepository.save(track9);
        trackRepository.save(track10);

        trackRepository.save(track11);
        trackRepository.save(track12);
        trackRepository.save(track13);
        trackRepository.save(track14);
        trackRepository.save(track15);

        trackRepository.save(track16);
        trackRepository.save(track17);
        trackRepository.save(track18);
        trackRepository.save(track19);
        trackRepository.save(track20);

        trackRepository.save(track21);
        trackRepository.save(track22);
        trackRepository.save(track23);
        trackRepository.save(track24);
        trackRepository.save(track25);

        trackRepository.save(track26);
        trackRepository.save(track27);
        trackRepository.save(track28);
        trackRepository.save(track29);
        trackRepository.save(track30);

        trackRepository.save(track31);
        trackRepository.save(track32);
        trackRepository.save(track33);
        trackRepository.save(track34);
        trackRepository.save(track35);

        trackRepository.save(track36);
        trackRepository.save(track37);
        trackRepository.save(track38);
        trackRepository.save(track39);
        trackRepository.save(track40);

        trackRepository.save(track41);
        trackRepository.save(track42);
        trackRepository.save(track43);
        trackRepository.save(track44);
        trackRepository.save(track45);

        trackRepository.save(track46);
        trackRepository.save(track47);
        trackRepository.save(track48);
        trackRepository.save(track49);
        trackRepository.save(track50);

        // Associate 5 tracks with each artist

        addTrackToArtist(artist1, track1);
        addTrackToArtist(artist1, track2);
        addTrackToArtist(artist1, track3);
        addTrackToArtist(artist1, track4);
        addTrackToArtist(artist1, track5);

        addTrackToArtist(artist2, track6);
        addTrackToArtist(artist2, track7);
        addTrackToArtist(artist2, track8);
        addTrackToArtist(artist2, track9);
        addTrackToArtist(artist2, track10);

        addTrackToArtist(artist3, track11);
        addTrackToArtist(artist3, track12);
        addTrackToArtist(artist3, track13);
        addTrackToArtist(artist3, track14);
        addTrackToArtist(artist3, track15);

        addTrackToArtist(artist4, track16);
        addTrackToArtist(artist4, track17);
        addTrackToArtist(artist4, track18);
        addTrackToArtist(artist4, track19);
        addTrackToArtist(artist4, track20);

        addTrackToArtist(artist5, track21);
        addTrackToArtist(artist5, track22);
        addTrackToArtist(artist5, track23);
        addTrackToArtist(artist5, track24);
        addTrackToArtist(artist5, track25);

        addTrackToArtist(artist6, track26);
        addTrackToArtist(artist6, track27);
        addTrackToArtist(artist6, track28);
        addTrackToArtist(artist6, track29);
        addTrackToArtist(artist6, track30);

        addTrackToArtist(artist7, track31);
        addTrackToArtist(artist7, track32);
        addTrackToArtist(artist7, track33);
        addTrackToArtist(artist7, track34);
        addTrackToArtist(artist7, track35);

        addTrackToArtist(artist8, track36);
        addTrackToArtist(artist8, track37);
        addTrackToArtist(artist8, track38);
        addTrackToArtist(artist8, track39);
        addTrackToArtist(artist8, track40);

        addTrackToArtist(artist9, track41);
        addTrackToArtist(artist9, track42);
        addTrackToArtist(artist9, track43);
        addTrackToArtist(artist9, track44);
        addTrackToArtist(artist9, track45);

        addTrackToArtist(artist10, track46);
        addTrackToArtist(artist10, track47);
        addTrackToArtist(artist10, track48);
        addTrackToArtist(artist10, track49);
        addTrackToArtist(artist10, track50);
    }

    private void addTrackToArtist(Artist artist, Track track) {

        artist.getTracks().add(track);
        track.getArtists().add(artist);
    }
}
