package com.example.discography.service;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;
import java.util.List;

public record ArtistDetails(Artist artist, List<Track> tracks) {
}
