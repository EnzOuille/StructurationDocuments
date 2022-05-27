package fr.ul.miage.structurationDocuments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import fr.ul.miage.structurationDocuments.modele.history.History;
import fr.ul.miage.structurationDocuments.modele.recommandation.Recommandation;
import fr.ul.miage.structurationDocuments.singleton.ConnectionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.Instant;

import static com.mongodb.client.model.Filters.eq;

/**
 * The type Local requestor.
 */
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
    private final int QUERY_TRACK = 8;

    /**
     * Instantiates a new Local requestor.
     */
    public LocalRequestor() {
        this.api = new ApiRequestor();
        this.connection = new ConnectionBDD();
    }

    /**
     * Gets detail.
     *
     * @param collection the collection
     * @param param      the param
     * @param state      the state
     * @param type       the type
     * @param prefix     the prefix
     * @return the detail
     */
    public String getDetail(MongoCollection<Document> collection, String param, int state, String type, String prefix) {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        StringBuilder stringBuilder = new StringBuilder();
        JsonObject resJson = new JsonObject();
        resJson.addProperty("query", type + " " + param);
        resJson.addProperty("query_date", Instant.now().toString());
        Document first = collection.find(eq(prefix + ".name", param)).first();
        if (first == null) {
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
                case QUERY_TRACK:
                    temp = this.api.getTrack(param);
                    break;
            }
            if (temp != null) {
                collection.insertOne(Document.parse(temp.toString()));
                resJson.add("result", temp);
                history.insertOne(Document.parse(resJson.toString()));
                return temp.toString();
            }else{
                resJson.add("result", null);
                return null;
            }
        }else{
            resJson.addProperty("type", "local");
            resJson.addProperty("result",stringBuilder.toString());
            history.insertOne(Document.parse(resJson.toString()));
            System.out.println("BEFORE RETURN");
            System.out.println(first.toJson());
            return first.toJson();
        }
    }

    /**
     * Gets top.
     *
     * @param collection the collection
     * @param param      the param
     * @param state      the state
     * @return the top
     */
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
        history.insertOne(Document.parse(resJson.toString()));
        return temp.toString();
    }

    /**
     * Gets tag.
     *
     * @param param the param
     * @return the tag
     */
    public String getTag(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_tag");
        return getDetail(collection, param, QUERY_TAG, "GetTag", "tag");
    }

    /**
     * Gets album.
     *
     * @param param the param
     * @return the album
     */
    public String getAlbum(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_album");
        return getDetail(collection, param, QUERY_ALBUM, "GetAlbum", "album");
    }

    /**
     * Gets artist.
     *
     * @param param the param
     * @return the artist
     */
    public String getArtist(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_artiste");
        return getDetail(collection, param, QUERY_ARTIST, "GetArtist", "artist");
    }

    /**
     * Top country tracks string.
     *
     * @param param the param
     * @return the string
     */
    public String topCountryTracks(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topCountryTracks");
        return getTop(collection, param, QUERY_TCTRACKS);
    }

    /**
     * Top country artists string.
     *
     * @param param the param
     * @return the string
     */
    public String topCountryArtists(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topCountryArtists");
        return getTop(collection, param, QUERY_TCARTISTS);
    }

    /**
     * Top artists string.
     *
     * @return the string
     */
    public String topArtists() {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topArtists");
        return getTop(collection, "", QUERY_TOPARTISTS);
    }

    /**
     * Top tags string.
     *
     * @return the string
     */
    public String topTags() {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topTags");
        return getTop(collection, "", QUERY_TOPTAGS);
    }

    /**
     * Top tracks string.
     *
     * @return the string
     */
    public String topTracks() {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topTracks");
        return getTop(collection, "", QUERY_TOPTRACKS);
    }

    /**
     * Format result string string.
     *
     * @param param the param
     * @return the string
     */
    public String formatResultString(String param) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject obj = new JsonParser().parse(param).getAsJsonObject();
        return gson.toJson(obj);
    }

    /**
     * Format result json string.
     *
     * @param param the param
     * @return the string
     */
    public String formatResultJson(JsonObject param) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(param);
    }

    /**
     * Add to history.
     *
     * @param jsonObject the json object
     */
    public void addToHistory(JsonObject jsonObject) {

    }

    /**
     * Gets track.
     *
     * @param param the param
     * @return the track
     */
    public String getTrack(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_track");
        return getDetail(collection, param, QUERY_TRACK, "GetTrack", "track");
    }

    /**
     * Insert recommandation.
     *
     * @param json the json
     */
    public void insertRecommandation(String json) {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_recommandation");
        history.insertOne(Document.parse(json));
    }

    /**
     * Gets history.
     *
     * @return the history
     */
    public ObservableList<History> getHistory() {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        FindIterable<Document> findIterable = history.find();
        ObservableList<History> list = FXCollections.observableArrayList();
        for (Document d : findIterable) {
            History h = new Gson().fromJson(d.toJson(), History.class);
            list.add(h);
        }
        return list;
    }

    public ObservableList<Recommandation> getRecommandation() {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_recommandation");
        FindIterable<Document> findIterable = history.find(Filters.eq("isValid",false));
        ObservableList<Recommandation> list = FXCollections.observableArrayList();
        for (Document d : findIterable) {
            Recommandation h = new Gson().fromJson(d.toJson(), Recommandation.class);
            list.add(h);
        }
        return list;
    }

    public void updateRecommandation(Recommandation recommandation) {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_recommandation");
        System.out.println(recommandation.get_id());
        Document document = history.find(Filters.eq("_id",new ObjectId(recommandation.get_id()))).first();
        Bson update = Updates.set("isValid",true);
        UpdateOptions options = new UpdateOptions().upsert(true);
        history.updateOne(document,update,options);
    }
}
