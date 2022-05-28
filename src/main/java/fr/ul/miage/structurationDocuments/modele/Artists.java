package fr.ul.miage.structurationDocuments.modele;

import fr.ul.miage.structurationDocuments.modele.artist.Artist;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Artists.
 */
public class Artists {

    private ArrayList<Artist> artist;

    /**
     * List to string string.
     *
     * @return the string
     */
    public String listToString() {
        return String.format("[%s]",artist.stream().map(Artist::getName).collect(Collectors.joining(", ")));
    }

    @Override
    public String toString() {
        return String.format("Artists: %s",this.listToString());
    }

    public ArrayList<Artist> getArtist() {
        return artist;
    }

}
