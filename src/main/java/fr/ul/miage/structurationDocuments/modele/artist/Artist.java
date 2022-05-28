package fr.ul.miage.structurationDocuments.modele.artist;

import fr.ul.miage.structurationDocuments.modele.Artists;

/**
 * The type Artist.
 */
public class Artist {

    private String name;
    private Artists similar;

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
        return String.format("Artist: %s\nSimilar: %s\n",name,similar.listToString());
    }

    public void setName(String name) {
        this.name = name;
    }
}
