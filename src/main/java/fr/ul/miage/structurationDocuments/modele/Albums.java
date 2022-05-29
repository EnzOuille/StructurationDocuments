package fr.ul.miage.structurationDocuments.modele;

import fr.ul.miage.structurationDocuments.modele.album.Album;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Albums.
 */
public class Albums {

    private ArrayList<Album> album;

    /**
     * List to string string.
     *
     * @return the string
     */
    public String listToString() {
        return String.format("%s",album.stream().map((x) -> String.format("\tNom: %s\n\tArtiste: %s\n\tLien: %s",x.getName(),x.getArtist(),x.getUrl())).collect(Collectors.joining(", ")));
    }

    @Override
    public String toString() {
        return String.format("Albums: %s",this.listToString());
    }
}
