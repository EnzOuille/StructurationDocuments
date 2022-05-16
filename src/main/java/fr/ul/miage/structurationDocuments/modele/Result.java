package fr.ul.miage.structurationDocuments.modele;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Result {

    @Override
    public String toString() {
        return super.toString();
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
