package fr.ul.miage.structurationDocuments.modele.album;

import fr.ul.miage.structurationDocuments.modele.Tracks;

/**
 * The type Album.
 */
public class Album {

    private String artist;
    private String name;
    private WikiAlbum wiki;
    private Tracks tracks;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nArtist: %s\nDuration: %s\n",name,artist,tracks.getTotalDuration());
    }
}
