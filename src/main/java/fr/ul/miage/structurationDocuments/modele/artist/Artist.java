package fr.ul.miage.structurationDocuments.modele.artist;

import fr.ul.miage.structurationDocuments.modele.Artists;

/**
 * The type Artist.
 */
public class Artist {

    private String name;

    private String listeners;

    private String url;
    private Artists similar;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    public String getListeners() {
        return listeners;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return String.format("Artist: %s\nSimilar: %s\n",name,similar.listToString());
    }

    public void setName(String name) {
        this.name = name;
    }
}
