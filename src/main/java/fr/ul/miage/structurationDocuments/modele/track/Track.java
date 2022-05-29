package fr.ul.miage.structurationDocuments.modele.track;

/**
 * The type Track.
 */
public class Track {

    private String name;

    private String url;

    private String listeners;
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

    public String getListeners() {
        return listeners;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
