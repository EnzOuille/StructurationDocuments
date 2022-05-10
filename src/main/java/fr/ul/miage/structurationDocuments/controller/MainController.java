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
    public Button btn_confirm;
    public ComboBox<String> combobox_type;
    public TextField input_type;
    public ComboBox<Integer> combobox_note;
    private LocalRequestor localRequestor;
    private ApiRequestor apiRequestor;

    public void initialize() {
        combobox_type.getItems().addAll("tag","album","track");
        combobox_note.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10);
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
            TagResult tag = new Gson().fromJson(this.apiRequestor.getTag(content), TagResult.class);
            this.listview_first.getItems().add(tag.toString());
        }
    }

    public void generate_album_results(ActionEvent actionEvent) {
        String content = input_album.getText();
        if (!content.isEmpty()) {
            content+="&artist="+input_artiste.getText();
            AlbumResult album = new Gson().fromJson(this.apiRequestor.getAlbum(content), AlbumResult.class);
            this.listview_first.getItems().add(album.toString());
        }
    }

    public void generate_artiste_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            ArtistResult artist = new Gson().fromJson(this.apiRequestor.getArtist(content), ArtistResult.class);
            this.listview_first.getItems().add(artist.toString());
        }
    }

    public void generate_topTracks_results(ActionEvent actionEvent) {
        TopTracksResult topTracks = new Gson().fromJson(this.apiRequestor.topTracks(), TopTracksResult.class);
        this.listview_second.getItems().add(topTracks.toString());
    }

    public void generate_topCountryTracks_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            TopTracksCountryResult topTracksCountry = new Gson().fromJson(this.apiRequestor.topCountryTracks(content), TopTracksCountryResult.class);
            this.listview_second.getItems().add(topTracksCountry.toString());
        }
    }

    public void generate_topArtists_results(ActionEvent actionEvent) {
        TopArtistsResult topArtists = new Gson().fromJson(this.apiRequestor.topArtists(), TopArtistsResult.class);
        this.listview_second.getItems().add(topArtists.toString());
    }

    public void generate_topTags_results(ActionEvent actionEvent) {
        TopTagsResult topTags = new Gson().fromJson(this.apiRequestor.topTags(), TopTagsResult.class);
        this.listview_second.getItems().add(topTags.toString());
    }

    public void generate_topCountryArtists_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            TopArtistsCountryResult topArtistsCountry = new Gson().fromJson(this.apiRequestor.topCountryArtists(content), TopArtistsCountryResult.class);
            this.listview_second.getItems().add(topArtistsCountry.toString());
        }
    }

    public void confirmRecommandation(ActionEvent actionEvent) {
        String type_value = this.input_type.getText();
        String note = String.valueOf(this.combobox_note.getItems().get(0));
        if (type_value.isEmpty() || note.isEmpty()) { return; }
        switch (this.combobox_type.getItems().get(0)) {
            case "tag":
                break;
            case "album":
                break;
            case "track":
                break;
        }
    }
}
