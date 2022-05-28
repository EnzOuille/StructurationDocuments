package fr.ul.miage.structurationDocuments;


import com.google.gson.JsonObject;

import java.util.Objects;

/**
 * The type Api requestor.
 */
public class ApiRequestor {

    private final HTTPTools httpTools;

    /**
     * Instantiates a new Api requestor.
     */
    public ApiRequestor() {
        this.httpTools = new HTTPTools();
    }

    /**
     * Gets artist.
     *
     * @param param the param
     * @return the artist
     */
    public JsonObject getArtist(String param) {
        String url = this.buildUrl("artist.search", "artist=" + param.replace(" ", "%20")  + "&limit=1");
        return httpTools.sendGet(url);
    }

    /**
     * Gets album.
     *
     * @param param the param
     * @return the album
     */
    public JsonObject getAlbum(String param) {
        String url = this.buildUrl("album.search", "album=" + param.replace(" ", "%20") + "&limit=1");
        return httpTools.sendGet(url);
    }

    /**
     * Gets tag.
     *
     * @param param the param
     * @return the tag
     */
    public JsonObject getTag(String param) {
        String url = this.buildUrl("tag.getinfo", "tag=" + param.replace(" ", "%20"));
        return httpTools.sendGet(url);
    }

    /**
     * Gets track.
     *
     * @param param the param
     * @return the track
     */
    public JsonObject getTrack(String param) {
        String url = this.buildUrl("track.search", "track=" + param.replace(" ", "%20") + "&limit=1");
        return httpTools.sendGet(url);
    }

    /**
     * Top country tracks json object.
     *
     * @param param the param
     * @return the json object
     */
    public JsonObject topCountryTracks(String param) {
        String url = this.buildUrl("geo.getTopTracks", "country=" + param.replace(" ", "%20") + "&limit=10");
        return httpTools.sendGet(url);
    }

    /**
     * Top country artists json object.
     *
     * @param param the param
     * @return the json object
     */
    public JsonObject topCountryArtists(String param) {
        String url = this.buildUrl("geo.getTopArtists", "country=" + param.replace(" ", "%20") + "&limit=10");
        return httpTools.sendGet(url);
    }

    /**
     * Top artists json object.
     *
     * @return the json object
     */
    public JsonObject topArtists() {
        String url = this.buildUrl("chart.getTopArtists", "limit=10");
        return httpTools.sendGet(url);
    }

    /**
     * Top tags json object.
     *
     * @return the json object
     */
    public JsonObject topTags() {
        String url = this.buildUrl("chart.getTopTags", "limit=10");
        return httpTools.sendGet(url);
    }

    /**
     * Top tracks json object.
     *
     * @return the json object
     */
    public JsonObject topTracks() {
        String url = this.buildUrl("chart.getTopTracks", "limit=10");
        return httpTools.sendGet(url);
    }

    /**
     * Build url string.
     *
     * @param method the method
     * @param param  the param
     * @return the string
     */
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
