package fr.ul.miage.structurationDocuments.singleton;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * The type Connection bdd.
 */
public class ConnectionBDD {

    private static ConnectionBDD instance;
    private final MongoClient connection;
    private final String url = "mongodb://localhost:27017";
    private final MongoDatabase database;
    //private final String API_KEY = "4fe5bfe5cdb20258458cbb9a8232bfac";

    /**
     * Instantiates a new Connection bdd.
     */
    public ConnectionBDD() {
        this.connection = MongoClients.create(url);
        database = connection.getDatabase("SD2022_projet");
    }

    /**
     * Gets database.
     *
     * @return the database
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public MongoClient getConnection() {
        return connection;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConnectionBDD getInstance() {
        if (instance == null) {
            instance = new ConnectionBDD();
        }
        return instance;
    }
}
