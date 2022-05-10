package fr.ul.miage.structurationDocuments.modele.artist;

import fr.ul.miage.structurationDocuments.modele.Artists;

public class Artist {

    private String name;
    private Artists similar;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Artist: %s\nSimilar: %s\n",name,similar.listToString());
    }
}
