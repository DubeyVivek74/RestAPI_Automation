package Basic.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import TESTAPI.APIRESTASSURED.Payloads;

public class BasicTest1 {

	public static void postReuest() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json").log().all()
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
						+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Frontline house\",\r\n"
						+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
						+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n"
						+ "    \"shoe park\",\r\n" + "    \"shop\"\r\n" + "  ],\r\n"
						+ "  \"website\": \"http://google.com\",\r\n" + "  \"language\": \"French-IN\"\r\n" + "}\r\n"
						+ "")
				.when().log().all().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200);

	}

	public static void MoreValidationsInRensponse() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").log().all()
				.body(Payloads.addPlace())
				.when().log().all().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("content-type", "application/json;charset=UTF-8").extract()
				.response().asString(); 
		System.out.println("Response is " + response); // We are trying to Print the response here by converting it to
														// asString() and Print that ,because we are not using log method in then
	}
// WE ARE PASTING BODY HERE INSTED OF THIS NOW i CAN PREPARE ACLASS AND PASTE BODY THERE ANS CALL IT so no need to paste complete String in Body
	public static void main(String[] args) {
		BasicTest1.MoreValidationsInRensponse();
      
	}

}
