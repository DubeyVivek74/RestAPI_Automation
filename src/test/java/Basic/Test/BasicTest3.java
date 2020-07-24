package Basic.Test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import TESTAPI.APIRESTASSURED.Payloads;
import TESTAPI.APIRESTASSURED.Utilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BasicTest3 {
	// THIS CLASS WILL TAKE PAYLOAD FROM EXTERNAL FILE AND PASS AS ARGUEMENTS TO
	// METHOD But again we need to change parameters before Runing ,
	// this is good only if payload is Fixed

	@Test()
	public void addBook() throws IOException {

		RestAssured.baseURI = "http://216.10.245.166";
		Response response = given().header("Content-Type", "application/json")
				.body(BasicTest3
						.GenerateString("C:\\Users\\Lenovo\\Desktop\\ApiAutomation\\APIRESTASSURED\\payload.json"))
				.log().all()

				.when().log().all().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200)
				.extract().response(); // body("ID",equalTo("bsckd9127"))
		System.out.println(response);
		JsonPath js = Utilities.rawtoJson(response);
		String bookId = js.get("ID");
		System.out.println(bookId);

	}

	public static String GenerateString(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
