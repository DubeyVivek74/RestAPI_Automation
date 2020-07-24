package Basic.Test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import TESTAPI.APIRESTASSURED.Utilities;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;


public class BasicTest4 {
// IN THIS WE ARE TRYING TO PASS PAYLOAD THROUGH HASHMAP



/*public static HashMap<String, Object> payloadMap() {
	HashMap<String,Object> hm = new HashMap<String,Object>(); //Map 1 
	HashMap<String,String> minihm = new HashMap<String,String>(); // Map 2 
	List<String> list = new ArrayList<String>();
	list.add("shoe park");
	list.add("shop");
	//foo.put("key1",list);
	minihm.put("lat"," -38.383494,");
	minihm.put("lng"," 33.427362,");
	hm.put("accuracy",50);
	hm.put("name","ABCHouse");
	hm.put("Phone_number","(+91) 983 893 3937");
	hm.put("address","29, side layout, cohen 09");
	hm.put("Types",list);
	hm.put("website","http://google.com");
	hm.put("language","French-IN");
	System.out.println("######"+hm);
	return hm;
	
	
	
}*/

@Test()
public void addBook() throws IOException {
	HashMap<String,Object> hm = new HashMap<String,Object>(); //Map 1 
	HashMap<String,String> minihm = new HashMap<String,String>(); // Map 2 
	List<String> list = new ArrayList<String>();
	list.add("shoe park");
	list.add("shop");
	//foo.put("key1",list);
	minihm.put("lat"," -38.383494,");
	minihm.put("lng"," 33.427362,");
	hm.put("location",minihm);
	hm.put("accuracy",50);
	hm.put("name","ABCHouse");
	hm.put("Phone_number","(+91) 983 893 3937");
	hm.put("address","29, side layout, cohen 09");
	hm.put("Types",list);
	hm.put("website","http://google.com");
	hm.put("language","French-IN");
	System.out.println("######"+hm);
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	Response  response = given().header("Content-Type", "application/json").queryParam("key", "qaclick123")
			.body(hm)
			.log().all()
			.when().log().all().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
			.extract().response();
	
	
	System.out.println(response);

	
	//I am trying to Pass hashmap into Body put while parsing the Json getting error 
	
	
}
}