package fr.ul.miage.structurationDocuments.modele.album;

import fr.ul.miage.structurationDocuments.modele.Albums;
import fr.ul.miage.structurationDocuments.modele.Result;

/**
 * The type Album result.
 */
public class AlbumResult extends Result {

    private static class Results  extends Result {
        /**
         * The Albummatches.
         */
        Albums albummatches;

        @Override
        public String toString() {
            return albummatches.listToString();
        }
    }

    private Results results;

    @Override
    public String toString() {
        return String.format("Album Result: \n%s",results);
    }
}
