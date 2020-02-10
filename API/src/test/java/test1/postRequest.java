package test1;

import org.json.JSONObject;
import org.testng.annotations.Test;

import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postRequest {
	@Test
	public void test1() {
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification request=RestAssured.given();
		JSONObject obj=new JSONObject();
		obj.put("name", "morpheus");
		obj.put("job", "leader");
		obj.put("name", "morpheus");
		obj.put("job", "leader");
		request.body(obj.toString());
		Response response=request.post("api/users/100");
		int response1=response.getStatusCode();
		System.out.println(response1);
		
		System.out.println(response.getStatusLine());
		Response response2=request.get("api/users/2");
		String s=response2.asString();
		System.out.println(s);

		

	}

}
