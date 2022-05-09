package fr.ul.miage.structurationDocuments.modele.artist;

import fr.ul.miage.structurationDocuments.modele.Artists;

import java.util.ArrayList;

public class Artist {

    private String name;
    private Artists similar;

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", similaire=" + similar +
                '}';
    }
}
