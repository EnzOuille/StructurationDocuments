package fr.ul.miage.structurationDocuments.modele.track;

import fr.ul.miage.structurationDocuments.modele.Result;
import fr.ul.miage.structurationDocuments.modele.Tracks;

/**
 * The type Track result.
 */
public class TrackResult extends Result {

    private static class Results {
        /**
         * The Trackmatches.
         */
        Tracks trackmatches;

        @Override
        public String toString() {
            return trackmatches.listToString();
        }
    }
    private Results results;

    @Override
    public String toString() {
        return String.format("Track Result: \n%s",results);
    }
}
