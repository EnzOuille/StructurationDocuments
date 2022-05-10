package fr.ul.miage.structurationDocuments.controller;

import com.google.gson.Gson;
import fr.ul.miage.structurationDocuments.ApiRequestor;
import fr.ul.miage.structurationDocuments.App;
import fr.ul.miage.structurationDocuments.LocalRequestor;
import fr.ul.miage.structurationDocuments.modele.album.AlbumResult;
import fr.ul.miage.structurationDocuments.modele.artist.ArtistResult;
import fr.ul.miage.structurationDocuments.modele.tag.TagResult;
import fr.ul.miage.structurationDocuments.modele.topartists.TopArtistsCountryResult;
import fr.ul.miage.structurationDocuments.modele.topartists.TopArtistsResult;
import fr.ul.miage.structurationDocuments.modele.toptags.TopTagsResult;
import fr.ul.miage.structurationDocuments.modele.toptracks.TopTracksCountryResult;
import fr.ul.miage.structurationDocuments.modele.toptracks.TopTracksResult;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

public class MainController {

    public static String user;
    public Tab tab_mod;
    public Tab tab_admin;
    public TabPane main_onglet;
    public ListView<String> listview_first;
    public TextField input_top_country_artist;
    public Button input_top_tracks;
    public TextField input_top_country_tracks;
    public Button input_top_artists;
    public Button input_top_tags;
    public ListView<String> listview_second;
    public TextField input_tag;
    public TextField input_album;
    public TextField input_artiste;
    private LocalRequestor localRequestor;
    private ApiRequestor apiRequestor;

    public void initialize() {
        this.localRequestor = new LocalRequestor();
        this.apiRequestor = new ApiRequestor();
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
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            TagResult tag = new Gson().fromJson(this.localRequestor.getTag(content), TagResult.class);
            this.listview_first.getItems().add(tag.toString());
        }
    }

    public void generate_album_results(ActionEvent actionEvent) {
        String content = input_album.getText();
        if (!content.isEmpty()) {
            content+="&artist="+input_artiste.getText();
            // Deux paramètres
            AlbumResult album = new Gson().fromJson(this.localRequestor.getAlbum(content), AlbumResult.class);
            this.listview_first.getItems().add(album.toString());
        }
    }

    public void generate_artiste_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            ArtistResult artist = new Gson().fromJson(this.localRequestor.getArtist(content), ArtistResult.class);
            this.listview_first.getItems().add(artist.toString());
        }
    }

    public void generate_topTracks_results(ActionEvent actionEvent) {
        TopTracksResult topTracks = new Gson().fromJson(this.localRequestor.topTracks(), TopTracksResult.class);
        this.listview_second.getItems().add(topTracks.toString());
    }

    public void generate_topCountryTracks_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            TopTracksCountryResult topTracksCountry = new Gson().fromJson(this.localRequestor.topCountryTracks(content), TopTracksCountryResult.class);
            this.listview_second.getItems().add(topTracksCountry.toString());
        }
    }

    public void generate_topArtists_results(ActionEvent actionEvent) {
        TopArtistsResult topArtists = new Gson().fromJson(this.localRequestor.topArtists(), TopArtistsResult.class);
        this.listview_second.getItems().add(topArtists.toString());
    }

    public void generate_topTags_results(ActionEvent actionEvent) {
        TopTagsResult topTags = new Gson().fromJson(this.localRequestor.topTags(), TopTagsResult.class);
        this.listview_second.getItems().add(topTags.toString());
    }

    public void generate_topCountryArtists_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            TopArtistsCountryResult topArtistsCountry = new Gson().fromJson(this.localRequestor.topCountryArtists(content), TopArtistsCountryResult.class);
            this.listview_second.getItems().add(topArtistsCountry.toString());
        }
    }
}
