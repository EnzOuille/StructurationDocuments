package fr.ul.miage.structurationDocuments.modele.album;

import fr.ul.miage.structurationDocuments.modele.Wiki;

/**
 * The type Wiki album.
 */
public class WikiAlbum extends Wiki {
    private String published;

    /**
     * Instantiates a new Wiki album.
     */
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
