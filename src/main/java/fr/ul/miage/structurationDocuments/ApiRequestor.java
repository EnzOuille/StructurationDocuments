package fr.ul.miage.structurationDocuments;


import com.google.gson.JsonObject;

import java.util.Objects;

public class ApiRequestor {

    private final HTTPTools httpTools;

    public ApiRequestor() {
        this.httpTools = new HTTPTools();
    }

    public JsonObject getArtist(String param) {
        String url = this.buildUrl("artist.getinfo", "artist=" + param);
        return httpTools.sendGet(url);
    }

    public JsonObject getAlbum(String param) {
        String url = this.buildUrl("album.getinfo", "album=" + param);
        return httpTools.sendGet(url);
    }

    public JsonObject getTag(String param) {
        String url = this.buildUrl("tag.getinfo", "tag=" + param);
        return httpTools.sendGet(url);
    }

    public JsonObject topCountryTracks(String param) {
        String url = this.buildUrl("geo.getTopTracks", "country=" + param);
        return httpTools.sendGet(url);
    }

    public JsonObject topCountryArtists(String param) {
        String url = this.buildUrl("geo.getTopArtists", "country=" + param);
        return httpTools.sendGet(url);
    }

    public JsonObject topArtists() {
        String url = this.buildUrl("chart.getTopArtists", "");
        return httpTools.sendGet(url);
    }

    public JsonObject topTags() {
        String url = this.buildUrl("chart.getTopTags", "");
        return httpTools.sendGet(url);
    }

    public JsonObject topTracks() {
        String url = this.buildUrl("chart.getTopTracks", "");
        return httpTools.sendGet(url);
    }

    public String buildUrl(String method, String param) {
        String uri = "http://ws.audioscrobbler.com/2.0/?";
        String api_key = "4fe5bfe5cdb20258458cbb9a8232bfac";
        String format = "json";
        if (Objects.equals(param, "")) {
            return String.format("%smethod=%s&api_key=%s&format=%s", uri, method, api_key, format);
        } else {
            return String.format("%smethod=%s&%s&api_key=%s&format=%s", uri, method, param, api_key, format);
        }
    }
}
