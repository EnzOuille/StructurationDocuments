package fr.ul.miage.structurationDocuments.modele.track;

public class Track {

    private String name;
    private int duration;

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
