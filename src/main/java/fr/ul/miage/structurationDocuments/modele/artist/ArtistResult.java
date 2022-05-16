package fr.ul.miage.structurationDocuments.modele.artist;

import fr.ul.miage.structurationDocuments.modele.Artists;

public class ArtistResult {

    private static class Results {
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
