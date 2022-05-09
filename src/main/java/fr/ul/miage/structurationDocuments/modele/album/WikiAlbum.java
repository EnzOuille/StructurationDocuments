package fr.ul.miage.structurationDocuments.modele.album;

import fr.ul.miage.structurationDocuments.modele.Wiki;

public class WikiAlbum extends Wiki {
    private String published;

    public WikiAlbum() {
        super();
    }

    @Override
    public String toString() {
        return "WikiAlbum{" +
                "published='" + published + '\'' +
                '}';
    }
}
