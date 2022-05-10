package fr.ul.miage.structurationDocuments.modele.artist;

public class ArtistResult {

    private Artist artist;

    @Override
    public String toString() {
        return String.format("Artist Result: \n%s",artist);
    }
}
