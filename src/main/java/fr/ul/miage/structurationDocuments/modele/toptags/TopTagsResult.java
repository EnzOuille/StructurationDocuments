package fr.ul.miage.structurationDocuments.modele.toptags;

import fr.ul.miage.structurationDocuments.modele.Result;
import fr.ul.miage.structurationDocuments.modele.Tags;

/**
 * The type Top tags result.
 */
public class TopTagsResult  extends Result {

    private Tags tags;

    @Override
    public String toString() {
        return String.format("Top 10 Artists: \n\t\t%s",tags.listToString());
    }
}
