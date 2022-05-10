package fr.ul.miage.structurationDocuments.modele.track;

public class Track {

    private String name;
    private int duration;

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
