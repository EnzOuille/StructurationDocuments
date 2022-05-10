package fr.ul.miage.structurationDocuments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoCollection;
import fr.ul.miage.structurationDocuments.singleton.ConnectionBDD;
import org.bson.Document;

import java.time.Instant;

import static com.mongodb.client.model.Filters.eq;

public class LocalRequestor {

    private final ApiRequestor api;
    private final ConnectionBDD connection;
    private final int QUERY_TAG = 0;
    private final int QUERY_ALBUM = 1;
    private final int QUERY_ARTIST = 2;
    private final int QUERY_TCTRACKS = 3;
    private final int QUERY_TCARTISTS = 4;
    private final int QUERY_TOPARTISTS = 5;
    private final int QUERY_TOPTAGS = 6;
    private final int QUERY_TOPTRACKS = 7;

    public LocalRequestor() {
        this.api = new ApiRequestor();
        this.connection = new ConnectionBDD();
    }

    public String getDetail(MongoCollection<Document> collection, String param, int state, String type) {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        StringBuilder stringBuilder = new StringBuilder();
        boolean hasResult = false;
        JsonObject resJson = new JsonObject();
        String res = "";
        resJson.addProperty("query", type);
        resJson.addProperty("query_date", Instant.now().toString());
        for (Document temp : collection.find(eq("name", param))) {
            stringBuilder.append(temp.toJson()).append("\n");
            hasResult = true;
        }
        if (!hasResult) {
            JsonObject temp = null;
            resJson.addProperty("type", "api");
            switch (state) {
                case QUERY_TAG:
                    temp = this.api.getTag(param);
                    break;
                case QUERY_ALBUM:
                    temp = this.api.getAlbum(param);
                    break;
                case QUERY_ARTIST:
                    temp = this.api.getArtist(param);
                    break;
            }
            if (temp != null) {
                collection.insertOne(Document.parse(temp.toString()));
                resJson.add("result", temp);
                history.insertOne(Document.parse(resJson.toString()));
                return temp.toString();
            }
            resJson.add("result", null);
            history.insertOne(Document.parse(resJson.toString()));
            return "Aucuns résultats";
        }
        resJson.addProperty("type", "local");
        resJson.addProperty("result",stringBuilder.toString());
        history.insertOne(Document.parse(resJson.toString()));
        return stringBuilder.toString();
    }

    public String getTop(MongoCollection<Document> collection, String param, int state) {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        JsonObject resJson = new JsonObject();
        JsonObject temp = null;
        String res = "";
        switch (state) {
            case QUERY_TCTRACKS:
                temp = this.api.topCountryTracks(param);
                res = "Query TopCountryTracks : " + param;
                break;
            case QUERY_TCARTISTS:
                temp = this.api.topCountryArtists(param);
                res = "Query TopCountryArtists : " + param;
                break;
            case QUERY_TOPARTISTS:
                temp = this.api.topArtists();
                res = "Query TopArtists";
                break;
            case QUERY_TOPTAGS:
                temp = this.api.topTags();
                res = "Query TopTags";
                break;
            case QUERY_TOPTRACKS:
                temp = this.api.topTracks();
                res = "Query TopTracks";
                break;
        }
        resJson.addProperty("query_date", Instant.now().toString());
        resJson.addProperty("query", res);
        resJson.add("result", temp);
        System.out.println("Insertion");
        System.out.println(Document.parse(resJson.toString()).toString());
        history.insertOne(Document.parse(resJson.toString()));
        return temp.toString();
    }

    public String getTag(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_tag");
        return getDetail(collection, param, QUERY_TAG, "GetTag");
    }

    public String getAlbum(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_album");
        return getDetail(collection, param, QUERY_ALBUM, "GetAlbum");
    }

    public String getArtist(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_artiste");
        return getDetail(collection, param, QUERY_ARTIST, "GetArtist");
    }

    public String topCountryTracks(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topCountryTracks");
        return getTop(collection, param, QUERY_TCTRACKS);
    }

    public String topCountryArtists(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topCountryArtists");
        return getTop(collection, param, QUERY_TCARTISTS);
    }

    public String topArtists() {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topArtists");
        return getTop(collection, "", QUERY_TOPARTISTS);
    }

    public String topTags() {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topTags");
        return getTop(collection, "", QUERY_TOPTAGS);
    }

    public String topTracks() {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topTracks");
        return getTop(collection, "", QUERY_TOPTRACKS);
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

    public void addToHistory(JsonObject jsonObject) {

    }
}
