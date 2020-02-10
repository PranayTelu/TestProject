package test1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getRequest {
	@Test
	public void firstt() {
		// Specify Base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";

		// Send Object
		RequestSpecification httpRequest = RestAssured.given();
		Response response = null;
		// Response Object
		
			response = httpRequest.get("");

			String resbody = response.getBody().asString();
			System.out.println("Json Body  " + resbody);
			System.out.println("Status Code  : " + response.getStatusCode());

			System.out.println("Status Line  : " + response.getStatusLine());
		

	}

}
