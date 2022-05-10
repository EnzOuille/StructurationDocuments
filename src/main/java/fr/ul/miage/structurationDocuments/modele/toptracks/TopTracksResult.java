package fr.ul.miage.structurationDocuments.modele.toptracks;

import fr.ul.miage.structurationDocuments.modele.Tracks;

public class TopTracksResult {

    private Tracks tracks;

    @Override
    public String toString() {
        return String.format("Top 10 Tracks: \n\t\t%s",tracks.listToString());
    }
}
