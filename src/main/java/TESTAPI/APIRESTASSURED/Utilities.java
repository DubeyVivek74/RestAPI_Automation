package TESTAPI.APIRESTASSURED;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utilities {

	
	public static JsonPath rawtoJson(Response r) {
		String respon=r.asString();
		JsonPath x=new JsonPath(respon);
		System.out.println(respon);
		return x; 
	}
	
	public static JsonPath responseToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}
}

