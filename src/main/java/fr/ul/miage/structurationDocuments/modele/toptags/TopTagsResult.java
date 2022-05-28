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
        try{
            return String.format("Top 10 Tags: \n\t\t%s",tags.listToString());
        }catch (NullPointerException e){
            return "Nothing";
        }
    }

    public Tags getTags() {
        return tags;
    }
}
