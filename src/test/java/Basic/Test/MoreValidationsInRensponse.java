package Basic.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import TESTAPI.APIRESTASSURED.Payload;
import io.restassured.RestAssured;

public class MoreValidationsInRensponse {
	//currently we cannot hard code Place id because it will be generated at
	// runtime so we need to store it
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =given().queryParam("key","qaclick123").header("Content-Type","application/json").log().all()
		.body(Payload.addPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("content-type","application/json;charset=UTF-8").extract().response().asString();  //currently we cannot hard code Place id because it will be generated at runtime so we need to store it 
		System.out.println("Response is "+response.toString());
		

	}

}
