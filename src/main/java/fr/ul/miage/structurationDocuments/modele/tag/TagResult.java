package fr.ul.miage.structurationDocuments.modele.tag;

public class TagResult {

    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return String.format("Tag Result: \n%s",tag.toString());
    }
}
