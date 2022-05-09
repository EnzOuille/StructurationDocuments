package fr.ul.miage.structurationDocuments.modele;

public class Wiki {

    private String summary;
    private String content;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("\t\t summary: %s\n\t\t content: %s", summary, content);
    }
}

