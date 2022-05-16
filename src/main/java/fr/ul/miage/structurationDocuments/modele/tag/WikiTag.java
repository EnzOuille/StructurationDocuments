package fr.ul.miage.structurationDocuments.modele.tag;

import fr.ul.miage.structurationDocuments.modele.Wiki;

/**
 * The type Wiki tag.
 */
public class WikiTag extends Wiki {

    /**
     * Instantiates a new Wiki tag.
     */
    public WikiTag() {
        super();
    }

    @Override
    public String toString() {
        return String.format("\t\t summary: %s\n\t\t content: %s", this.getSummary(), this.getContent());
    }
}
