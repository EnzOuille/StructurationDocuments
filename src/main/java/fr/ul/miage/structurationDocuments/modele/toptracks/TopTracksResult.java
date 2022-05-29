package fr.ul.miage.structurationDocuments.modele.toptracks;

import fr.ul.miage.structurationDocuments.modele.Result;
import fr.ul.miage.structurationDocuments.modele.Tracks;

/**
 * The type Top tracks result.
 */
public class TopTracksResult  extends Result {

    private Tracks tracks;

    @Override
    public String toString() {
        try{
            return String.format("Top 10 Tracks: \n%s",tracks.listToString());
        }catch (NullPointerException e){
            return "Nothing";
        }
    }

    public Tracks getTracks() {
        return tracks;
    }
}
