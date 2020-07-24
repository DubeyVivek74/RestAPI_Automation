package Basic.Test;

import TESTAPI.APIRESTASSURED.Mock;
import TESTAPI.APIRESTASSURED.Utilities;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class ComplexJson {
	static int sum;
	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Mock.coursePrice());
		int count = js.get("courses.size()");
		System.out.println("courses count is " + count); // 1.
		int purchaseamount = js.get("dashboard.purchaseAmount"); // 2
		System.out.println("purchase amount is " + purchaseamount);
		String firstCourseTitle = js.get("courses[0].title"); // 3
		// we need to print title of first course , couse is array , so if we want 1st
		// element of array then so Course[0].title or price
		System.out.println("FirstCourseTitle is " + firstCourseTitle);
		for (int i = 0; i < count; i++) {
			String courses = js.get("courses[" + i + "].title"); // 4
			String prize = js.get("courses[" + i + "].price").toString();
			System.out.println(courses + " and " + prize);
			if (courses.equalsIgnoreCase("RPA")) { // 5

				String copies = js.get("courses[" + i + "].copies").toString();
				System.out.println("RPA course copies are " + copies);
				break;
			}

		}

		// 6

		// sum is static variable
		for (int i = 0; i < count; i++) {
			int price = js.get("courses[" + i + "].price");
			int copies = js.get("courses[" + i + "].copies");
			int amount = price * copies;
			System.out.println("Total Courses amount is " + amount);
			sum = sum + amount;

		}
		System.out.println("Total SUM is " + sum);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount is " + purchaseAmount);
		Assert.assertEquals(purchaseAmount, sum);

	}

}


/*1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount*/

/*{
	  "dashboard": {
	    "purchaseAmount": 910,
	    "website": "rahulshettyacademy.com"
	  },
	  "courses": [
	    {
	      "title": "Selenium Python",
	      "price": 50,
	      "copies": 6
	    },
	    {
	      "title": "Cypress",
	      "price": 40,
	      "copies": 4
	    },
	    {
	      "title": "RPA",
	      "price": 45,
	      "copies": 10
	    }
	  ]
	}
*/