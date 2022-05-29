package fr.ul.miage.structurationDocuments.modele;

import fr.ul.miage.structurationDocuments.modele.track.Track;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Tracks.
 */
public class Tracks {

    private ArrayList<Track> track;

    /**
     * Gets total duration.
     *
     * @return the total duration
     */
    public int getTotalDuration() {
        return track.stream().mapToInt(Track::getDuration).sum();
    }

    /**
     * List to string string.
     *
     * @return the string
     */
    public String listToString() {
        return String.format("\t%s",track.stream().map((x) -> String.format("Nom: %s\n\tNombre d'écoutes: %s\n\tLien: %s\n",x.getName(),x.getListeners(),x.getUrl())).collect(Collectors.joining("\n")));
    }

    @Override
    public String toString() {
        return String.format("Tracks: %s",this.listToString());
    }

    public ArrayList<Track> getTrack() {
        return track;
    }
}
