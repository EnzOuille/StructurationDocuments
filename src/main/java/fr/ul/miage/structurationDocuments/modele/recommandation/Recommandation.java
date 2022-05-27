package fr.ul.miage.structurationDocuments.modele.recommandation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.ul.miage.structurationDocuments.controller.MainController;

/**
 * The type Recommandation.
 */
public class Recommandation {

    private class ObjectId {
        private String $oid;

        @Override
        public String toString() {
            return $oid;
        }
    }

    private ObjectId _id;

    private final String comment;

    private final int notation;

    private final String user;

    private String type_content;

    private String content;

    private final boolean isValid;

    /**
     * Instantiates a new Recommandation.
     *
     * @param comment  the comment
     * @param notation the notation
     */
    public Recommandation(String comment, int notation) {
        this.comment = comment;
        this.notation = notation;
        this.user = MainController.user;
        this.isValid=false;
    }

    /**
     * Sets content.
     *
     * @param content the content
     * @param type
     */
    public void setContent(String content, String type) {
        this.content = content;
       this.type_content=type;
    }

    /**
     * Sets type content.
     *
     * @param type_content the type content
     */
    public void setType_content(String type_content) {
        this.type_content = type_content;
    }

    @Override
    public String toString() {
        return String.format("Recommandation %s :\n" +
                "\tUtilisateur : %s\n" +
                "\tCommentaire : %s\n" +
                "\tType de contenu : %s\n" +
                "\tNote : %s",_id,user,comment,type_content,notation);
    }

    public String get_id() {
        return _id.toString();
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
