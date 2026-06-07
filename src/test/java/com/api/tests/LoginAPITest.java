package com.api.tests;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.SpecUtil.*;

public class LoginAPITest 
{
	
	@Test
	public void loginAPITest() throws IOException
	{
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
			.spec(requestSpec(userCredentials))
		.when()
			.post("login")
			.then()
			.spec(responseSpec_OK())
			.and()
			.body("message", equalTo("Success"))
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
			
	}
}
