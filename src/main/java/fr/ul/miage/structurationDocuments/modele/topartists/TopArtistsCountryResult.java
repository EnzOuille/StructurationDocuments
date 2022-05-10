package fr.ul.miage.structurationDocuments.modele.topartists;

import fr.ul.miage.structurationDocuments.modele.Artists;

public class TopArtistsCountryResult {

    private Artists topartists;

    @Override
    public String toString() {
        return String.format("Top 10 Artists by Country: \n\t\t%s",topartists.listToString());
    }
}
