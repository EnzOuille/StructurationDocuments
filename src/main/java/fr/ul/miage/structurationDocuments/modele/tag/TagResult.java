package fr.ul.miage.structurationDocuments.modele.tag;

import fr.ul.miage.structurationDocuments.modele.Result;

/**
 * The type Tag result.
 */
public class TagResult extends Result {

    private Tag tag;

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return String.format("Tag Result: \n%s",tag.toString());
    }
}
