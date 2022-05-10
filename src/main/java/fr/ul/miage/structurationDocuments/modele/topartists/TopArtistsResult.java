package fr.ul.miage.structurationDocuments.modele.topartists;

import fr.ul.miage.structurationDocuments.modele.Artists;

public class TopArtistsResult {

    private Artists artists;

    @Override
    public String toString() {
        return String.format("Top 10 Artists: \n\t\t%s",artists.listToString());
    }
}
