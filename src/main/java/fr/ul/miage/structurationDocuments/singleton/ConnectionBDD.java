package fr.ul.miage.structurationDocuments.singleton;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnectionBDD {

    private static ConnectionBDD instance;
    private final MongoClient connection;
    private final MongoCollection<Document> collection;
    private final String url = "mongodb://localhost:27017";
    private final String API_KEY = "4fe5bfe5cdb20258458cbb9a8232bfac";

    private ConnectionBDD() {
        this.connection = MongoClients.create(url);
        MongoDatabase database = connection.getDatabase("miage");
        this.collection = database.getCollection("paris");
    }

    public MongoClient getConnection() {
        return connection;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public static ConnectionBDD getInstance() {
        if (instance == null) {
            instance = new ConnectionBDD();
        }
        return instance;
    }
}
