package fr.ul.miage.structurationDocuments.controller;

import fr.ul.miage.structurationDocuments.App;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

public class MainController {

    public static String user;
    public Tab tab_mod;
    public Tab tab_admin;
    public TabPane main_onglet;
    public ListView listview_first;
    public TextField input_top_country_artist;
    public Button input_top_tracks;
    public Button input_top_country_tracks;
    public Button input_top_artists;
    public Button input_top_tags;
    public ListView listview_second;
    public TextField input_tag;
    public TextField input_album;
    public TextField input_artiste;

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

    public void generate_tag_results(ActionEvent actionEvent) {
    }

    public void generate_album_results(ActionEvent actionEvent) {
    }

    public void generate_artiste_results(ActionEvent actionEvent) {
    }

    public void generate_topTracks_results(ActionEvent actionEvent) {
    }

    public void generate_topCountryTracks_results(ActionEvent actionEvent) {
    }

    public void generate_topArtists_results(ActionEvent actionEvent) {
    }

    public void generate_topTags_results(ActionEvent actionEvent) {
    }
}
