package fr.ul.miage.structurationDocuments.modele;

/**
 * The type Wiki.
 */
public class Wiki {

    private String summary;
    private String content;

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("\t\t summary: %s\n\t\t content: %s", summary, content);
    }
}

