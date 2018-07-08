package com.Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

public class PolarityAnalyzer 
{
    
	JSONObject getSentiment(String text) throws ParseException, UnirestException
    {
		HttpResponse<JsonNode> response = null;
    	try
    	{
    	response = Unirest.post("https://twinword-sentiment-analysis.p.mashape.com/analyze/")
    			.header("X-Mashape-Key", "4wKznV7I02mshGpKv5mXf4rBoGFSp1HABEzjsncV0K975wnLh6")
    			.header("Content-Type", "application/x-www-form-urlencoded")
    			.header("Accept", "application/json")
    			.field("text", text)
    			.asJson();
    	}
    	catch(Exception e)
    	{
    		
    	}
    	
    		String s = response.getBody().toString();
    		JSONParser parser = new JSONParser();
    		Object obj = parser.parse(s);
    		JSONObject jsonObject = (JSONObject) obj;
    	
    		return jsonObject;
   }
    

}

   


