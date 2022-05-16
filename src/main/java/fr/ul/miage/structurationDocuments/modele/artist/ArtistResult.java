package fr.ul.miage.structurationDocuments.modele.artist;

import fr.ul.miage.structurationDocuments.modele.Artists;
import fr.ul.miage.structurationDocuments.modele.Result;

/**
 * The type Artist result.
 */
public class ArtistResult extends Result{

    private static class Results extends Result {
        /**
         * The Artistmatches.
         */
        Artists artistmatches;

        @Override
        public String toString() {
            return artistmatches.listToString();
        }
    }

    private Results results;

    @Override
    public String toString() {
        return String.format("Artist Result: \n%s",results);
    }
}
