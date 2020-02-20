package test1;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class putRequest {
	
	@Test
	public void test1() {
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification request=RestAssured.given();
		Response response=request.put("/api/users/2");
		JSONObject obj=new JSONObject();
		obj.put("name", "morpheus");
		obj.put("job", "zion resident");
		request.body(obj.toString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getStatusCode());
		
		Response response1=request.get("/api/users/2");
		System.out.println(response1.body().asString());


		
		}

}
