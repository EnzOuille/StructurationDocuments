package fr.ul.miage.structurationDocuments.modele;

import fr.ul.miage.structurationDocuments.modele.track.Track;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tracks {

    private ArrayList<Track> track;

    public int getTotalDuration() {
        return track.stream().mapToInt(Track::getDuration).sum();
    }

    public String listToString() {
        return String.format("[%s]",track.stream().map(Track::getName).collect(Collectors.joining(", ")));
    }

    @Override
    public String toString() {
        return String.format("Tracks: %s",this.listToString());
    }
}
