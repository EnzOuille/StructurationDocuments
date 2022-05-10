package fr.ul.miage.structurationDocuments.modele;

import fr.ul.miage.structurationDocuments.modele.tag.Tag;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tags {

    private ArrayList<Tag> tag;

    public String listToString() {
        return String.format("[%s]",tag.stream().map(Tag::getName).collect(Collectors.joining(", ")));
    }

    @Override
    public String toString() {
        return String.format("Tags: %s",this.listToString());
    }
}
