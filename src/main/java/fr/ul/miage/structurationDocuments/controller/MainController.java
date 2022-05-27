package fr.ul.miage.structurationDocuments.controller;

import com.google.gson.Gson;
import fr.ul.miage.structurationDocuments.App;
import fr.ul.miage.structurationDocuments.LocalRequestor;
import fr.ul.miage.structurationDocuments.modele.Result;
import fr.ul.miage.structurationDocuments.modele.album.AlbumResult;
import fr.ul.miage.structurationDocuments.modele.artist.ArtistResult;
import fr.ul.miage.structurationDocuments.modele.history.History;
import fr.ul.miage.structurationDocuments.modele.recommandation.Recommandation;
import fr.ul.miage.structurationDocuments.modele.tag.TagResult;
import fr.ul.miage.structurationDocuments.modele.topartists.TopArtistsCountryResult;
import fr.ul.miage.structurationDocuments.modele.topartists.TopArtistsResult;
import fr.ul.miage.structurationDocuments.modele.toptags.TopTagsResult;
import fr.ul.miage.structurationDocuments.modele.toptracks.TopTracksCountryResult;
import fr.ul.miage.structurationDocuments.modele.toptracks.TopTracksResult;
import fr.ul.miage.structurationDocuments.modele.track.TrackResult;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * The type Main controller.
 */
public class MainController {

    /**
     * The constant user.
     */
    public static String user;
    /**
     * The Tab mod.
     */
    public Tab tab_mod;
    /**
     * The Tab admin.
     */
    public Tab tab_admin;
    /**
     * The Main onglet.
     */
    public TabPane main_onglet;
    /**
     * The Listview first.
     */
    public ListView<Result> listview_first;
    /**
     * The Input top country artist.
     */
    public TextField input_top_country_artist;
    /**
     * The Input top tracks.
     */
    public Button input_top_tracks;
    /**
     * The Input top country tracks.
     */
    public TextField input_top_country_tracks;
    /**
     * The Input top artists.
     */
    public Button input_top_artists;
    /**
     * The Input top tags.
     */
    public Button input_top_tags;
    /**
     * The Listview second.
     */
    public ListView<Result> listview_second;
    /**
     * The Input tag.
     */
    public TextField input_tag;
    /**
     * The Input album.
     */
    public TextField input_album;
    /**
     * The Input artiste.
     */
    public TextField input_artiste;
    /**
     * The Input track.
     */
    public TextField input_track;
    /**
     * The History anchor.
     */
    public AnchorPane history_anchor;
    public ListView recom_listview;
    private LocalRequestor localRequestor;

    /**
     * Initialize.
     */
    public void initialize() {
        this.localRequestor = new LocalRequestor();
        switch (user) {
            case "utilisateur":
                this.main_onglet.getTabs().removeAll(tab_admin, tab_mod);
                break;
            case "modérateur":
                this.main_onglet.getTabs().removeAll(tab_admin);
                break;
        }
        System.out.println(user);
    }

    public void generate_recommandation() {
        this.recom_listview.getItems().clear();
        ObservableList<Recommandation> recommandations = this.localRequestor.getRecommandation();
        this.recom_listview.getItems().addAll(recommandations);
    }

    /**
     * Generate history datas.
     */
    public void generate_history_datas() {
        TableView<History> table_history = new TableView<>();
        TableColumn<History, String> query
                = new TableColumn<>("query");
        TableColumn<History, String> query_date
                = new TableColumn<>("query_date");
        TableColumn<History, String> type
                = new TableColumn<>("type");

        query.setCellValueFactory(new PropertyValueFactory<>("query"));
        query_date.setCellValueFactory(new PropertyValueFactory<>("query_date"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        table_history.getColumns().addAll(query_date, query, type);
        ObservableList<History> history = this.localRequestor.getHistory();
        table_history.getItems().addAll(history);
        this.history_anchor.getChildren().clear();
        AnchorPane.setBottomAnchor(table_history, 0.0);
        AnchorPane.setLeftAnchor(table_history, 0.0);
        AnchorPane.setRightAnchor(table_history, 0.0);
        AnchorPane.setTopAnchor(table_history, 0.0);
        this.history_anchor.getChildren().add(table_history);
    }

    /**
     * Permet de se déconnecter de l'application
     *
     * @param event évènement
     */
    public void end_connection(Event event) {
        App.changeScene("/javafx/connection.fxml");
    }

    /**
     * Generate tag results.
     *
     * @param actionEvent the action event
     */
    public void generate_tag_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            TagResult tag = new Gson().fromJson(this.localRequestor.getTag(content), TagResult.class);
            this.listview_first.getItems().add(tag);
        }
    }

    /**
     * Generate album results.
     */
    public void generate_album_results() {
        String content = input_album.getText();
        if (!content.isEmpty()) {
            AlbumResult album = new Gson().fromJson(this.localRequestor.getAlbum(content), AlbumResult.class);
            this.listview_first.getItems().add(album);
        }
    }

    /**
     * Generate artiste results.
     *
     * @param actionEvent the action event
     */
    public void generate_artiste_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            ArtistResult artist = new Gson().fromJson(this.localRequestor.getArtist(content), ArtistResult.class);
            this.listview_first.getItems().add(artist);
        }
    }

    /**
     * Generate top tracks results.
     *
     * @param actionEvent the action event
     */
    public void generate_topTracks_results(ActionEvent actionEvent) {
        TopTracksResult topTracks = new Gson().fromJson(this.localRequestor.topTracks(), TopTracksResult.class);
        this.listview_second.getItems().add(topTracks);
    }

    /**
     * Generate top country tracks results.
     *
     * @param actionEvent the action event
     */
    public void generate_topCountryTracks_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            TopTracksCountryResult topTracksCountry = new Gson().fromJson(this.localRequestor.topCountryTracks(content), TopTracksCountryResult.class);
            this.listview_second.getItems().add(topTracksCountry);
        }
    }

    /**
     * Generate top artists results.
     *
     * @param actionEvent the action event
     */
    public void generate_topArtists_results(ActionEvent actionEvent) {
        TopArtistsResult topArtists = new Gson().fromJson(this.localRequestor.topArtists(), TopArtistsResult.class);
        this.listview_second.getItems().add(topArtists);
    }

    /**
     * Generate top tags results.
     *
     * @param actionEvent the action event
     */
    public void generate_topTags_results(ActionEvent actionEvent) {
        TopTagsResult topTags = new Gson().fromJson(this.localRequestor.topTags(), TopTagsResult.class);
        this.listview_second.getItems().add(topTags);
    }

    /**
     * Generate top country artists results.
     *
     * @param actionEvent the action event
     */
    public void generate_topCountryArtists_results(ActionEvent actionEvent) {
        String content = ((TextField)actionEvent.getSource()).getText();
        if (!content.isEmpty()) {
            TopArtistsCountryResult topArtistsCountry = new Gson().fromJson(this.localRequestor.topCountryArtists(content), TopArtistsCountryResult.class);
            this.listview_second.getItems().add(topArtistsCountry);
        }
    }

    /**
     * Add recomm.
     *
     * @throws IOException the io exception
     */
    public void addRecomm() throws IOException {
        String selected = listview_first.getSelectionModel().getSelectedItem().toString();
        if (selected.contains("Tag") || selected.contains("Album") || selected.contains("Track")) {
            String type = "Track";
            if (selected.contains("Tag")) {
                type="Tag";
            } else if (selected.contains("Album")) {
                type="Album";
            }
            Stage stage = new Stage();
            stage.setTitle("Projet - Structuration Document (Recommandation)");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafx/alert.fxml"));
            AnchorPane mainpage = loader.load();
            Scene scene = new Scene(mainpage);
            stage.setScene(scene);
            ((AlertController) loader.getController()).setTextAlertContent(listview_first.getSelectionModel().getSelectedItem(),type);
            stage.show();
        }
    }

    /**
     * Generate track results.
     */
    public void generate_track_results() {
        String content = input_track.getText();
        if (!content.isEmpty()) {
            TrackResult track = new Gson().fromJson(this.localRequestor.getTrack(content), TrackResult.class);
            this.listview_first.getItems().add(track);
        }
    }

    /**
     * On selection changed.
     */
    public void onSelectionChangedHisto() {
        generate_history_datas();
    }

    public void onSelectionChangedRecom() {
        generate_recommandation();
    }

    public void onClickedValidateRecom() {
        Recommandation recommandation = (Recommandation) recom_listview.getSelectionModel().getSelectedItem();
        if (recommandation != null) {
            this.localRequestor.updateRecommandation(recommandation);
            generate_recommandation();
        }
    }
}
