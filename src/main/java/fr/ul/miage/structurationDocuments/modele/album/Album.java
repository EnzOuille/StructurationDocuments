package fr.ul.miage.structurationDocuments.modele.album;

import fr.ul.miage.structurationDocuments.modele.Tracks;
import fr.ul.miage.structurationDocuments.modele.track.Track;

import java.util.ArrayList;

public class Album {

    private String artist;
    private String name;
    private WikiAlbum wiki;
    private Tracks tracks;

    @Override
    public String toString() {
        return String.format("Name: %s\nArtist: %s\nDuration: %s\n",name,artist,tracks.getTotalDuration());
    }
}
