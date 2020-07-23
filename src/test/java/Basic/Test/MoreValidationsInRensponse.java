package Basic.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import TESTAPI.APIRESTASSURED.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class MoreValidationsInRensponse {
	//currently we cannot hard code Place id because it will be generated at
	// runtime so we need to store it
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").log().all()
				.body(Payload.addPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("content-type", "application/json;charset=UTF-8")
				.extract().response().asString(); // currently we cannot hard code Place id because it will be generated
// at runtime so we need to store it. so using JsonPath also to add I'm using log() as well as converting response to String so it will print response body 2 times 
		System.out.println("Response is " + response.toString());
		JsonPath js = new JsonPath(response);
		String place_id = js.get("place_id");
		System.out.println(place_id);

	}

}
