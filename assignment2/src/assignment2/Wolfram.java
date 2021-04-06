package assignment2;


import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.mashape.unirest.http.HttpResponse;

public class Wolfram {
	private String APIID = "AKPEQ2-VR5VV3W29E";
	private String HOST = "http://api.wolframalpha.com/v1/result";
	private String CHARSET = "UTF-8";
	
	// takes in string and checks if wolfram has a response using short answers api
	// returns a string response or null 
	public String getWolframSimpleResponse(String input) {
		// parameter for get request
		String query = "";
		try {
			query = String.format("i=%s",
				       URLEncoder.encode(input, CHARSET));
		} catch (UnsupportedEncodingException e) {
			// if unable to encode query
			System.out.println("problem encoding in wolfram");
			return null;
		}
		
		try {
			
			HttpResponse<String> httpResponse = Unirest.get(HOST + "?appid=" + APIID + "&" + query).asString();
			// check staus of request 
			
			if(httpResponse.getStatus() == 200) {
				System.out.println(httpResponse.getBody());
				String output = httpResponse.getBody();
				if(output.contains("Wolfram|Alpha did not understand your input")){
					// if theres no good response return null
					return null;
				}
				else {
					return output;
				}
			}else {
				System.out.println("status not 200 ");
			}
			
		} catch (UnirestException e) {
			//return null
		}
		return null;
	}
}
