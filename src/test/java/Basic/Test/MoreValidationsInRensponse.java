package Basic.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import TESTAPI.APIRESTASSURED.Payloads;
import TESTAPI.APIRESTASSURED.Utilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class MoreValidationsInRensponse {
	
	 // E2E testcases for PUSH PUT AND GET 
	//currently we cannot hard code Place id because it will be generated at
	// runtime so we need to store it
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").log().all()
				.body(Payloads.addPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("content-type", "application/json;charset=UTF-8")
				.extract().response().asString(); // currently we cannot hard code Place id because it will be generated
// at runtime so we need to store it. so using JsonPath also to add I'm using log() as well as converting response to String so it will print response body 2 times 
		System.out.println("Response is " + response.toString());
		JsonPath js = new JsonPath(response);
		String place_id = js.get("place_id");
		System.out.println("**********"+place_id);
		
		//To update address  we are using PUT 
		String newAddress ="Old Slough ,UK";
	String putresponse=	given().queryParam("key", "qaclick123").header("Content-Type", "application/json").log().all().body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).log().all().body("msg", equalTo("Address successfully updated")).extract().response().asString();
		  System.out.println(putresponse);  //HERE adding As String doesn't help because we already are using equalTo
		
		 // To retrive the updated Address we are using GET
		  
		Response getResponse=  given().queryParam("key", "qaclick123").queryParam("place_id",place_id).log().all()
		   .when().get("/maps/api/place/get/json")
		  .then().assertThat().statusCode(200).extract().response();
		//JsonPath js1 =new JsonPath(getResponse);  /// we will create re-use able method for JSONPath 
		   JsonPath js1=  Utilities.rawtoJson(getResponse);
		String updatedAddress =js1.get("address").toString();
		System.out.println(updatedAddress);
		Assert.assertTrue(newAddress.equalsIgnoreCase(updatedAddress));
		System.out.println("longitude is " +js1.get("location.longitude"));  // if we have below response then we can extract by js.get("location.longitude)
	    /*"location": {
	        "latitude": "-38.383494",
	        "longitude": "33.427362"
	    },*/
		
		
	}

}
