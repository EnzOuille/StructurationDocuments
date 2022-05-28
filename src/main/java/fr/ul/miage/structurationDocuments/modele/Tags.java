package fr.ul.miage.structurationDocuments.modele;

import fr.ul.miage.structurationDocuments.modele.tag.Tag;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Tags.
 */
public class Tags {

    private ArrayList<Tag> tag;

    /**
     * List to string string.
     *
     * @return the string
     */
    public String listToString() {
        return String.format("[%s]",tag.stream().map(Tag::getName).collect(Collectors.joining(", ")));
    }

    @Override
    public String toString() {
        return String.format("Tags: %s",this.listToString());
    }

    public ArrayList<Tag> getTag() {
        return tag;
    }
}
