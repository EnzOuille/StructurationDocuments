package fr.ul.miage.structurationDocuments.singleton;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnectionBDD {

    private static ConnectionBDD instance;
    private final MongoClient connection;
    private final String url = "mongodb://localhost:27017";
    private final MongoDatabase database;
    //private final String API_KEY = "4fe5bfe5cdb20258458cbb9a8232bfac";

    public ConnectionBDD() {
        this.connection = MongoClients.create(url);
        database = connection.getDatabase("SD2022_projet");
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoClient getConnection() {
        return connection;
    }

    public static ConnectionBDD getInstance() {
        if (instance == null) {
            instance = new ConnectionBDD();
        }
        return instance;
    }
}
