package TESTAPI.APIRESTASSURED;

import io.restassured.path.json.JsonPath;

public class Utilities {

	
	public static JsonPath rawtoJson(String response) {
		JsonPath js1 =new JsonPath(response);
		return js1;/// 
	}
}
