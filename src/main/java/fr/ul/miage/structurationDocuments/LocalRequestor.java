package fr.ul.miage.structurationDocuments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.ul.miage.structurationDocuments.singleton.ConnectionBDD;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class LocalRequestor {

    private ApiRequestor api;
    private ConnectionBDD connection;

    public LocalRequestor(){
        this.api = new ApiRequestor();
        this.connection = new ConnectionBDD();
    }

    public String getArtist(String param) {
        MongoCollection<Document> collection = connection.getDatabase().getCollection("GECCT_artiste");
        Document doc = collection.find(eq("name", param)).first();
        if (doc != null){
            return formatResultString(doc.toJson());
        }else{
            return formatResultJson(api.getArtist(param));
        }
    }

//    public String getAlbum(String param) {
//        String url = this.buildUrl("album.getinfo", "album=" + param);
//        return httpTools.sendGet(url);
//    }
//
//    public String getTag(String param) {
//        String url = this.buildUrl("tag.getinfo", "tag=" + param);
//        return httpTools.sendGet(url);
//    }
//
//    public String topCountryTracks(String param) {
//        String url = this.buildUrl("geo.getTopTracks", "country=" + param);
//        return httpTools.sendGet(url);
//    }
//
//    public String topCountryArtists(String param) {
//        String url = this.buildUrl("geo.getTopArtists", "country=" + param);
//        return httpTools.sendGet(url);
//    }
//
//    public String topArtists(String param) {
//        String url = this.buildUrl("chart.getTopArtists", "");
//        return httpTools.sendGet(url);
//    }
//
//    public String topTags(String param) {
//        String url = this.buildUrl("chart.getTopTags", "");
//        return httpTools.sendGet(url);
//    }
//
//    public String topTracks(String param) {
//        String url = this.buildUrl("chart.getTopTracks", "");
//        return httpTools.sendGet(url);
//    }

    public String formatResultString(String param){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject obj = new JsonParser().parse(param).getAsJsonObject();
        return gson.toJson(obj);
    }

    public String formatResultJson(JsonObject param){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(param);
    }
}
