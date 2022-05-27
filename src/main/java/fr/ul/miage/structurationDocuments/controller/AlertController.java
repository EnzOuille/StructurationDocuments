package fr.ul.miage.structurationDocuments.controller;

import fr.ul.miage.structurationDocuments.LocalRequestor;
import fr.ul.miage.structurationDocuments.modele.Result;
import fr.ul.miage.structurationDocuments.modele.recommandation.Recommandation;
import fr.ul.miage.structurationDocuments.modele.track.TrackResult;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Alert controller.
 */
public class AlertController {

    public static String type;
    /**
     * The Alert content.
     */
    /**
     * The Combobox note.
     */
    public ComboBox<Integer> combobox_note;
    /**
     * The Confirm content.
     */
    public Button confirm_content;
    /**
     * The Cancel content.
     */
    public Button cancel_content;
    /**
     * The Textarea commentaire.
     */
    public TextArea textarea_commentaire;
    /**
     * The Alert content.
     */
    public ListView<Result> alert_content;

    /**
     * Initialize.
     */
    public void initialize() {
        combobox_note.getItems().addAll(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 5, 6, 7, 8, 9, 10)));
    }

    /**
     * Sets text alert content.
     *
     * @param content the content
     */
    public void setTextAlertContent(Result content,String type) {
        alert_content.getItems().add(0,content);
        AlertController.type=type;
    }

    /**
     * Send recom.
     *
     * @param actionEvent the action event
     */
    public void send_recom(ActionEvent actionEvent) {
        if (combobox_note.getSelectionModel().getSelectedItem() == null || this.textarea_commentaire.getText().isEmpty()) {return;}
        int note = combobox_note.getSelectionModel().getSelectedItem();
        String comment = textarea_commentaire.getText();
        Recommandation recommandation = new Recommandation(comment,note);
        recommandation.setContent(this.alert_content.getItems().get(0).toJson(),type);

        LocalRequestor localRequestor = new LocalRequestor();
        localRequestor.insertRecommandation(recommandation.toJson());
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Cancel recom.
     *
     * @param actionEvent the action event
     */
    public void cancel_recom(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
