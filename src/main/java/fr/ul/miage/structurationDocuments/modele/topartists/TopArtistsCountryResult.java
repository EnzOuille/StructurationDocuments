package fr.ul.miage.structurationDocuments.modele.topartists;

import fr.ul.miage.structurationDocuments.modele.Artists;
import fr.ul.miage.structurationDocuments.modele.Result;

/**
 * The type Top artists country result.
 */
public class TopArtistsCountryResult  extends Result {

    private Artists topartists;

    @Override
    public String toString() {
        try{
            return String.format("Top 10 Artists by Country: \n\t\t%s",topartists.listToString());
        }catch (NullPointerException e){
            return "Nothing";
        }
    }

    public Artists getTopartists() {
        return topartists;
    }
}
