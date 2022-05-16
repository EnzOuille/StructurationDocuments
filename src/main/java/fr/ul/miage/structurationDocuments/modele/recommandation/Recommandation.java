package fr.ul.miage.structurationDocuments.modele.recommandation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.ul.miage.structurationDocuments.controller.MainController;

public class Recommandation {

    private final String comment;

    private final int notation;

    private final String user;

    private String type_content;

    private String content;

    private final boolean isValid;

    public Recommandation(String comment, int notation) {
        this.comment = comment;
        this.notation = notation;
        this.user = MainController.user;
        this.isValid=false;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void setType_content(String type_content) {
        this.type_content = type_content;
    }

    @Override
    public String toString() {
        return "Recommandation{" +
                "comment='" + comment + '\'' +
                ", notation=" + notation +
                ", user='" + user + '\'' +
                ", type_content='" + type_content + '\'' +
                ", content='" + content + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
