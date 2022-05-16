package fr.ul.miage.structurationDocuments.modele.track;

/**
 * The type Track.
 */
public class Track {

    private String name;
    private int duration;

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
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
