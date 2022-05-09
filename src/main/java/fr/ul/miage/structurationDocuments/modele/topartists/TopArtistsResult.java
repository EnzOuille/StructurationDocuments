package fr.ul.miage.structurationDocuments.modele.topartists;

import fr.ul.miage.structurationDocuments.modele.Artists;

public class TopArtistsResult {

    private Artists artists;

    @Override
    public String toString() {
        return "TopArtistsResult{" +
                "topArtists=" + artists +
                '}';
    }
}
