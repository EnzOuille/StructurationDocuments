package fr.ul.miage.structurationDocuments;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main(String[] args){
        String uri = "mongodb://localhost:27017";
        try(MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("miage");
            MongoCollection<Document> collection = database.getCollection("paris");
            Document doc = collection.find(eq("category", "restaurant")).first();
            assert doc != null;
            System.out.println(doc.toJson());
        }
    }
}
