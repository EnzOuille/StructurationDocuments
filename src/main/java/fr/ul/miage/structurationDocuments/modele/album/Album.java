package fr.ul.miage.structurationDocuments.modele.album;

import java.util.ArrayList;

public class Album {

    private String artist;
    private String name;
    private WikiAlbum wiki;
    private ArrayList<Track> tracks;

    @Override
    public String toString() {
        return "Album{" +
                "artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                ", wiki=" + wiki +
                ", tracks=" + tracks +
                '}';
    }
}
