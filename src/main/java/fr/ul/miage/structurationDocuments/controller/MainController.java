package fr.ul.miage.structurationDocuments.controller;

import fr.ul.miage.structurationDocuments.App;
import javafx.event.Event;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController {

    public static String user;
    public Tab tab_mod;
    public Tab tab_admin;
    public TabPane main_onglet;

    public void initialize() {
        switch (user) {
            case "utilisateur":
                this.main_onglet.getTabs().removeAll(tab_admin,tab_mod);
                break;
            case "modérateur":
                this.main_onglet.getTabs().removeAll(tab_admin);
                break;
        }
        System.out.println(user);
    }

    /**
     * Permet de se déconnecter de l'application
     * TODO --> Déconnecter au sens de la BDD l'utilisation/admin/modo
     * @param event évènement
     */
    public void end_connection(Event event) {
        App.changeScene("/javafx/connection.fxml");
    }
}
