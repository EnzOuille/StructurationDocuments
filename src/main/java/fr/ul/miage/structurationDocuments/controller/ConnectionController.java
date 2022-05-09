package fr.ul.miage.structurationDocuments.controller;

import fr.ul.miage.structurationDocuments.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ConnectionController {

    @FXML
    private ComboBox<String> user_combobox;

    @FXML
    private Button user_connexion;

    public void initialize() {
        user_combobox.getItems().addAll("utilisateur","modérateur","administrateur");
    }


    /**
     * Permet de connecter un utilisateur à l'application
     * TODO --> lancer la connection BDD selon la selection du combobox
     * @param actionEvent évènement
     */
    public void start_connection(ActionEvent actionEvent) {
        String connection_type = user_combobox.getValue();
        App.changeScene("/javafx/main.fxml");
    }
}
