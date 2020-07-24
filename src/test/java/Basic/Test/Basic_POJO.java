package Basic.Test;

import org.testng.annotations.Test;

import TESTAPI.APIRESTASSURED.GetPlace;
import TESTAPI.APIRESTASSURED.Getlocation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class Basic_POJO {
GetPlace p ;
Getlocation l;
//IN THIS CLASS I TRIED SERIALIAZE AND DESERIALIZE MY PAYLOAD CONTAINS ARRAY SO I USED ARRAY LIST TO STORE BUT GOT ERROR SO CHANGED THE TYPE 
// NEED TO WORK ON THIS .CURRENTLY THIS TC IS PASSING
	@Test(priority=1)
	public void PostPojo() {
		p = new GetPlace();
		l= new Getlocation();
		l.setLatitude(-38.383494);
		l.setLongitude(33.427362);
		//List<String> type = new ArrayList<String>();
		//type.add("Shop");
		//type.add("Book");
		p.setAccuracy("50");
		p.setAddress("BlueLine House Slough, UK");
		p.setLanguage("German");
		p.setName("Ronaldo");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("Testwebsite2gems.com");
		p.setTypes("Shoe");
		
		//p.setTypes(type);
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response res =given().queryParam("key", "qaclick123").body(p).log().all().when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
	     System.out.println(res+"@@@@@@@@@@");
		
		
		
	}
	@Test(priority=2)
	public void GetPojo() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		GetPlace gp = given().queryParam("key", "qaclick123").queryParam("place_id", "7fb9783b9c2e82c88e9928efc627cd9c")
				.expect().defaultParser(Parser.JSON).when().get("maps/api/place/get/json").as(GetPlace.class);

		double gf = gp.getLocation().getLatitude();
		System.out.println(gf);
		   
		     
		
	}
}
