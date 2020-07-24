package Basic.Test;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TESTAPI.APIRESTASSURED.Payloads;
import TESTAPI.APIRESTASSURED.Utilities;

public class BasicTest2 {
//TRYING TO PASS PAYLOAD DYNAMICALLY via PARAMETERIZED METHODS AND DATAPROVIDER
	@Test(enabled=false)
	public void addBook() {
		
		RestAssured.baseURI="http://216.10.245.166";
		Response response =given().header("Content-Type","application/json").body(Payloads.addBook("svnd","457")).log().all() 
		//We are creating a method which will accept 2 string so we can give book info dynamically , verify go to addBook		
		.when().log().all().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response(); //body("ID",equalTo("bsckd9127"))
		// equal to assertion will fail because we need to give it always ,So using JSONPATH
		System.out.println(response);
		JsonPath js =  Utilities.rawtoJson(response);
		String bookId =js.get("ID");
		System.out.println(bookId);   
		
	}	
	
	@Test(dataProvider="BooksData")
	public void addBook1(String isbn, String aile) {
		
		RestAssured.baseURI="http://216.10.245.166";
		Response response =given().header("Content-Type","application/json").body(Payloads.addBook(isbn,aile)).log().all() 
		//We are creating a method which will accept 2 string so we can give book info dynamically , verify go to addBook		
		.when().log().all().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response(); //body("ID",equalTo("bsckd9127"))
		// equal to assertion will fail because we need to give it always ,So using JSONPATH
		System.out.println(response);
		JsonPath js =  Utilities.rawtoJson(response);
		String bookId =js.get("ID");
		System.out.println(bookId);   
		
	}
	//Trying to use Data Provider for parameterize these values.
		
		
		@DataProvider(name="BooksData")
		public  Object[][] getData(){
			return new Object[][] {{"helium","5067"},{"Ransom","4153"},{"Merc","7177"}};
		}
		
// the Problem here is if we run once it's working fine but if we run again Tc's will be failed as all Id's are created so Random generation will help

	

}
