package fr.ul.miage.structurationDocuments.modele.album;

public class AlbumResult {

    private Album album;

    @Override
    public String toString() {
        return String.format("Album Result: \n%s",album);
    }
}
