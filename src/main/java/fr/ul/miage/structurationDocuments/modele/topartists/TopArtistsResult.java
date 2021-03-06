package fr.ul.miage.structurationDocuments.modele.topartists;

import fr.ul.miage.structurationDocuments.modele.Artists;
import fr.ul.miage.structurationDocuments.modele.Result;

/**
 * The type Top artists result.
 */
public class TopArtistsResult  extends Result {

    private Artists artists;

    @Override
    public String toString() {
        try{
            return String.format("Top 10 Artists: \n\t\t%s",artists.listToString());
        } catch (NullPointerException e){
            return "Nothing";
        }
    }

    public Artists getArtists() {
        return artists;
    }
}
