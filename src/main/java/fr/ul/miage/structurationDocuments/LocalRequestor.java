package fr.ul.miage.structurationDocuments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import fr.ul.miage.structurationDocuments.modele.Result;
import fr.ul.miage.structurationDocuments.modele.artist.Artist;
import fr.ul.miage.structurationDocuments.modele.history.History;
import fr.ul.miage.structurationDocuments.modele.recommandation.Recommandation;
import fr.ul.miage.structurationDocuments.modele.tag.Tag;
import fr.ul.miage.structurationDocuments.modele.topartists.TopArtistsCountryResult;
import fr.ul.miage.structurationDocuments.modele.topartists.TopArtistsResult;
import fr.ul.miage.structurationDocuments.modele.toptags.TopTagsResult;
import fr.ul.miage.structurationDocuments.modele.toptracks.TopTracksCountryResult;
import fr.ul.miage.structurationDocuments.modele.toptracks.TopTracksResult;
import fr.ul.miage.structurationDocuments.modele.track.Track;
import fr.ul.miage.structurationDocuments.singleton.ConnectionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.Instant;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

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
            } else {
                resJson.add("result", null);
                return null;
            }
        } else {
            resJson.addProperty("type", "local");
            resJson.add("result", new JsonParser().parse(first.toJson()));
            history.insertOne(Document.parse(resJson.toString()));
            System.out.println("BEFORE RETURN");
            System.out.println(first.toJson());
            return first.toJson();
        }
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
    public Result topCountryTracks(String param) {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        JsonObject tempJson = new JsonObject();
        Bson sort = Sorts.descending("query_date");
        JsonObject temp = this.api.topCountryTracks(param);
        String res = "Query TopCountryTracks : " + param;
        MongoCollection<Document> topCT = connection.getDatabase().getCollection("GECCT_topCountryTracks");
        tempJson.addProperty("query_date", Instant.now().toString());
        tempJson.addProperty("query", res);
        tempJson.add("result", temp);
        String pattern = ".?TopCountryTracks : " + param + ".?";
        Document first = topCT.find(regex("query", pattern)).sort(sort).first();
        topCT.insertOne(Document.parse(tempJson.toString()));
        history.insertOne(Document.parse(tempJson.toString()));
        TopTracksCountryResult topTracksCountry1 = new Gson().fromJson(temp, TopTracksCountryResult.class);
        if (first != null) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(first.toJson(), JsonObject.class);
            TopTracksCountryResult topTracksCountry2 = new Gson().fromJson(jsonObject.get("result"), TopTracksCountryResult.class);
            for (int i = 0; i < 10; i++) {
                Track temp1 = topTracksCountry1.getTracks().getTrack().get(i);
                Track temp2 = topTracksCountry2.getTracks().getTrack().get(i);
                if (temp1.getName().equals(temp2.getName())) {
                    topTracksCountry1.getTracks().getTrack().get(i).setName(temp1.getName() + " (STAY)");
                } else {
                    topTracksCountry1.getTracks().getTrack().get(i).setName(temp1.getName() + " (NEW)");
                }
            }
            return topTracksCountry1;
        } else {
            return topTracksCountry1;
        }
    }

    /**
     * Top country artists string.
     *
     * @param param the param
     * @return the string
     */
    public Result topCountryArtists(String param) {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        JsonObject tempJson = new JsonObject();
        Bson sort = Sorts.descending("query_date");
        JsonObject temp = this.api.topCountryArtists(param);
        String res = "Query TopCountryArtists : " + param;
        MongoCollection<Document> topCA = connection.getDatabase().getCollection("GECCT_topCountryArtists");
        tempJson.addProperty("query_date", Instant.now().toString());
        tempJson.addProperty("query", res);
        tempJson.add("result", temp);
        String pattern = ".?TopCountryArtists : " + param + ".?";
        Document first = topCA.find(regex("query", pattern)).sort(sort).first();
        topCA.insertOne(Document.parse(tempJson.toString()));
        history.insertOne(Document.parse(tempJson.toString()));
        TopArtistsCountryResult topArtistsCountry1 = new Gson().fromJson(temp, TopArtistsCountryResult.class);
        if (first != null) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(first.toJson(), JsonObject.class);
            TopArtistsCountryResult topArtistsCountry2 = new Gson().fromJson(jsonObject.get("result"), TopArtistsCountryResult.class);
            for (int i = 0; i < 10; i++) {
                Artist temp1 = topArtistsCountry1.getTopartists().getArtist().get(i);
                Artist temp2 = topArtistsCountry2.getTopartists().getArtist().get(i);
                if (temp1.getName().equals(temp2.getName())) {
                    topArtistsCountry1.getTopartists().getArtist().get(i).setName(temp1.getName() + " (STAY)");
                } else {
                    topArtistsCountry2.getTopartists().getArtist().get(i).setName(temp1.getName() + " (NEW)");
                }
            }
            return topArtistsCountry1;
        } else {
            return topArtistsCountry1;
        }
    }

    /**
     * Top artists string.
     *
     * @return the string
     */
    public Result topArtists() {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        JsonObject tempJson = new JsonObject();
        Bson sort = Sorts.descending("query_date");
        JsonObject temp = this.api.topArtists();
        String res = "Query TopArtists";
        MongoCollection<Document> topA = connection.getDatabase().getCollection("GECCT_topArtists");
        tempJson.addProperty("query_date", Instant.now().toString());
        tempJson.addProperty("query", res);
        tempJson.add("result", temp);
        String pattern = ".?TopArtists.?";
        Document first = topA.find(regex("query", pattern)).sort(sort).first();
        topA.insertOne(Document.parse(tempJson.toString()));
        history.insertOne(Document.parse(tempJson.toString()));
        TopArtistsResult topArtists1 = new Gson().fromJson(temp, TopArtistsResult.class);
        if (first != null) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(first.toJson(), JsonObject.class);
            TopArtistsResult topArtists2 = new Gson().fromJson(jsonObject.get("result"), TopArtistsResult.class);
            for (int i = 0; i < 10; i++) {
                Artist temp1 = topArtists1.getArtists().getArtist().get(i);
                Artist temp2 = topArtists2.getArtists().getArtist().get(i);
                if (temp1.getName().equals(temp2.getName())) {
                    topArtists1.getArtists().getArtist().get(i).setName(temp1.getName() + " (STAY)");
                } else {
                    topArtists2.getArtists().getArtist().get(i).setName(temp1.getName() + " (NEW)");
                }
            }
            return topArtists1;
        } else {
            return topArtists1;
        }
    }

    /**
     * Top tags string.
     *
     * @return the string
     */
    public Result topTags() {
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        JsonObject tempJson = new JsonObject();
        Bson sort = Sorts.descending("query_date");
        JsonObject temp = this.api.topTags();
        String res = "Query TopTags";
        MongoCollection<Document> topTags = connection.getDatabase().getCollection("GECCT_topTags");
        tempJson.addProperty("query_date", Instant.now().toString());
        tempJson.addProperty("query", res);
        tempJson.add("result", temp);
        String pattern = ".?TopTags.?";
        Document first = topTags.find(regex("query", pattern)).sort(sort).first();
        topTags.insertOne(Document.parse(tempJson.toString()));
        history.insertOne(Document.parse(tempJson.toString()));
        TopTagsResult topTags1 = new Gson().fromJson(temp, TopTagsResult.class);
        if (first != null) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(first.toJson(), JsonObject.class);
            TopTagsResult topTags2 = new Gson().fromJson(jsonObject.get("result"), TopTagsResult.class);
            for (int i = 0; i < 10; i++) {
                Tag temp1 = topTags1.getTags().getTag().get(i);
                Tag temp2 = topTags2.getTags().getTag().get(i);
                if (temp1.getName().equals(temp2.getName())) {
                    topTags1.getTags().getTag().get(i).setName(temp1.getName() + " (STAY)");
                } else {
                    topTags2.getTags().getTag().get(i).setName(temp1.getName() + " (NEW)");
                }
            }
            return topTags1;
        } else {
            return topTags1;
        }
    }

    /**
     * Top tracks string.
     *
     * @return the string
     */
    public Result topTracks() {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_topTracks");
        MongoCollection<Document> history = connection.getDatabase().getCollection("GECCT_history");
        JsonObject tempJson = new JsonObject();
        Bson sort = Sorts.descending("query_date");
        JsonObject temp = this.api.topTracks();
        String res = "Query TopTracks";
        MongoCollection<Document> topTracks = connection.getDatabase().getCollection("GECCT_topTracks");
        tempJson.addProperty("query_date", Instant.now().toString());
        tempJson.addProperty("query", res);
        tempJson.add("result", temp);
        String pattern = ".?TopTracks.?";
        Document first = topTracks.find(regex("query", pattern)).sort(sort).first();
        topTracks.insertOne(Document.parse(tempJson.toString()));
        history.insertOne(Document.parse(tempJson.toString()));
        TopTracksResult topTracks1 = new Gson().fromJson(temp, TopTracksResult.class);
        if (first != null) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(first.toJson(), JsonObject.class);
            TopTracksResult topTracks2 = new Gson().fromJson(jsonObject.get("result"), TopTracksResult.class);
            for (int i = 0; i < 10; i++) {
                Track temp1 = topTracks1.getTracks().getTrack().get(i);
                Track temp2 = topTracks2.getTracks().getTrack().get(i);
                if (temp1.getName().equals(temp2.getName())) {
                    topTracks1.getTracks().getTrack().get(i).setName(temp1.getName() + " (STAY)");
                } else {
                    topTracks2.getTracks().getTrack().get(i).setName(temp1.getName() + " (NEW)");
                }
            }
            return topTracks1;
        } else {
            return topTracks1;
        }
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
        FindIterable<Document> findIterable = history.find(Filters.eq("isValid", false));
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
        Document document = history.find(Filters.eq("_id", new ObjectId(recommandation.get_id()))).first();
        Bson update = Updates.set("isValid", true);
        UpdateOptions options = new UpdateOptions().upsert(true);
        history.updateOne(document, update, options);
    }


}
