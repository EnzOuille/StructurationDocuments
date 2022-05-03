package fr.ul.miage.structurationDocuments.controller;

import fr.ul.miage.structurationDocuments.App;
import javafx.event.Event;

public class MainController {

    /**
     * Permet de se déconnecter de l'application
     * TODO --> Déconnecter au sens de la BDD l'utilisation/admin/modo
     * @param event évènement
     */
    public void end_connection(Event event) {
        App.changeScene("/javafx/connection.fxml");
    }
}
