package fr.ul.miage.structurationDocuments.modele.toptags;

import fr.ul.miage.structurationDocuments.modele.Tags;

import java.util.ArrayList;

public class TopTagsResult {

    private Tags tags;

    @Override
    public String toString() {
        return String.format("Top 10 Artists: \n\t\t%s",tags.listToString());
    }
}
