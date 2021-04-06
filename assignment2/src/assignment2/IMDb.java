package assignment2;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;

public class IMDb {
	private String APIID = "e1853634";
	private String HOST = "http://www.omdbapi.com/";
	private String CHARSET = "UTF-8";
	
	public String getIMDbResponse(String input) {
		input.replace("?","");
		String query = "";
		try {
			// make query
			query = String.format("t=%s",
				       URLEncoder.encode(input, CHARSET));
		} catch (UnsupportedEncodingException e) {	
			// if unable to encode query
			System.out.println("problem encoding in IMDb input");
			return null;
		}
		try {
		HttpResponse<String> response = Unirest.get(HOST + "?apikey=" + APIID + "&" + query)
				.header("x-rapidapi-key", APIID)
				.header("x-rapidapi-host", HOST)
				.asString();
		String output = response.getBody();
		if(!output.equals("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}")) {
			// make json object so we can parse the data
			JSONObject obj = new JSONObject(output);
			// returns plot of movie
			return "Of course! " + obj.getString("Plot");
		}
		}catch(UnirestException e) {
			System.out.println("unable to make query");
			
		}
		return null;
	}	

}
