package com.api.tests;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class UserDetailsAPITest 
{
	@Test
	public void userDetailsAPITest() throws IOException
	{
		Header authHeader = new Header("Authorization", getToken(FD));
		
		given()
			.baseUri(getProperty("BASE_URI"))
			.contentType(ContentType.JSON)
			.header(authHeader)
			.accept(ContentType.JSON)
			.log().uri()
			.log().method()
			.log().body()
			.log().headers()
		.when()
			.get("userdetails")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1500L))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
			
	}
}
