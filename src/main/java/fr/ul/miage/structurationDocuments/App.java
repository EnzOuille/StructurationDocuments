package fr.ul.miage.structurationDocuments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * The type App.
 */
public class App extends Application {

    private static Stage stage;

    /**
     * The constant current_user.
     */
    public static String current_user;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Projet - Structuration Document");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/javafx/connection.fxml"));
        AnchorPane mainpage = loader.load();
        Scene scene = new Scene(mainpage);
        stage.setScene(scene);
        stage.show();
        App.stage=stage;
    }

    /**
     * Change scene.
     *
     * @param location the location
     */
    public static void changeScene(String location) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(location));
            Parent mainpage = loader.load();
            Scene scene = new Scene(mainpage);
            App.stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args){
        String uri = "mongodb://localhost:27017";
        try(MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("miage");
            MongoCollection<Document> collection = database.getCollection("paris");
            Document doc = collection.find(eq("category", "restaurant")).first();
            assert doc != null;
            System.out.println(doc.toJson());
        }
    }*/
}
