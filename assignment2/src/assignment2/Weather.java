package assignment2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Weather {
	private String APIID = "2d325a1dfd5dadbd3cd4854fa5990a32";
	private String HOST = "http://api.openweathermap.org/data/2.5/weather";
	private String CHARSET = "UTF-8";
	
	public String getWeatherResponse(String input) {
		input.replace("?","");
		String query = "";
		try {
			// make query
			query = String.format("q=%s",
				       URLEncoder.encode(input, CHARSET));
		} catch (UnsupportedEncodingException e) {	
			// if unable to encode query
			System.out.println("problem encoding in Weather input");
			return null;
		}
		try {
			System.out.println(HOST + "?" + query + "&appid=" + APIID);
			HttpResponse<String> response = Unirest.get(HOST + "?" + query + "&appid=" + APIID)
				.asString();
		
			if(response.getStatus() == 200) {
				String output = response.getBody();
				// make json object so we can parse the data
				JSONObject obj = new JSONObject(output);
				// get current temperature
				double curTemp = obj.getJSONObject("main").getDouble("temp");
				String description = obj.getJSONArray("weather").getJSONObject(0).getString("description");
				
				// convert weather to celius and keep 2 decimal points
				String result = String.format("%.2f", curTemp - 273.5);
				// return current temp and description
				return "The current weather in " + input + " is " + description + " and the tempurture is " + result  + " Celius";
			}
		}catch(UnirestException e) {
			System.out.println("unable to make query");
			
		}
		return null;
	}	

}

