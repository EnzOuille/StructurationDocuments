package fr.ul.miage.structurationDocuments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import fr.ul.miage.structurationDocuments.singleton.ConnectionBDD;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class LocalRequestor {

    private final ApiRequestor api;
    private final ConnectionBDD connection;

    public LocalRequestor() {
        this.api = new ApiRequestor();
        this.connection = new ConnectionBDD();
    }

    public String getTag(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_tag");
        StringBuilder stringBuilder = new StringBuilder();
        boolean hasResult = false;
        for (Document temp : collection.find(eq("name", param))) {
            stringBuilder.append(temp.toJson()).append("\n");
            hasResult = true;
        }
        if (!hasResult){
            JsonObject temp = this.api.getTag(param);
            if (temp != null){
                collection.insertOne(Document.parse(temp.toString()));
                return temp.toString();
            }
            return "Aucuns résultats";
        }
        return stringBuilder.toString();
    }

    public String getAlbum(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_album");
        StringBuilder stringBuilder = new StringBuilder();
        for (Document temp : collection.find(eq("name", param))) {
            stringBuilder.append(temp.toJson()).append("\n");
        }
        return stringBuilder.toString();
    }

    public String getArtist(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_artiste");
        StringBuilder stringBuilder = new StringBuilder();
        for (Document temp : collection.find(eq("name", param))) {
            stringBuilder.append(temp.toJson()).append("\n");
        }
        return stringBuilder.toString();
    }

    public String topCountryTracks(String param) {
        return this.api.topCountryTracks(param).toString();
    }

    public String topCountryArtists(String param) {
        return this.api.topCountryArtists(param).toString();
    }

    public String topArtists() {
        return this.api.topArtists().toString();
    }

    public String topTags() {
        return this.api.topTags().toString();
    }

    public String topTracks() {
        return this.api.topTracks().toString();
    }

    public String formatResultString(String param) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject obj = new JsonParser().parse(param).getAsJsonObject();
        return gson.toJson(obj);
    }

    public String formatResultJson(JsonObject param) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(param);
    }
}
