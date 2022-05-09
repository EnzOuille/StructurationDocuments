package fr.ul.miage.structurationDocuments.modele.tag;

import fr.ul.miage.structurationDocuments.modele.Wiki;

public class WikiTag extends Wiki {

    public WikiTag() {
        super();
    }

    @Override
    public String toString() {
        return String.format("\t\t summary: %s\n\t\t content: %s", this.getSummary(), this.getContent());
    }
}
