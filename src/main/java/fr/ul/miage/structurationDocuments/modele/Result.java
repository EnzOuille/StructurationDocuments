package fr.ul.miage.structurationDocuments.modele;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The type Result.
 */
public class Result {

    @Override
    public String toString() {
        return super.toString();
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
