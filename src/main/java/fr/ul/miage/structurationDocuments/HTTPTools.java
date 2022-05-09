package fr.ul.miage.structurationDocuments;//package Nom-de-votre-paquet ;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Outils pour HTTP
 *
 */
public class HTTPTools {
	// temps minimum d'une requête HTTP en ms (4 seconds)
	private int mt = 4000;

	// dernière requête HTTP
	private long last;

	public HTTPTools(){

	}
	/**
	 * Envoi une requête GET
	 * @param url de la requête
	 * @return reponse
	 */
	public JsonObject sendGet(String url) {
		// vérifie le temps écoulé depuis la requête précédente
		while (System.currentTimeMillis() - last < mt);
		last = System.currentTimeMillis();
		
		try {
			// préparation de la requête
			StringBuilder result = new StringBuilder();
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			
			// obtention de la réponse
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			
			// fermeture du lecteur et retour
			br.close();
			isr.close();
			return new JsonParser().parse(result.toString()).getAsJsonObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
