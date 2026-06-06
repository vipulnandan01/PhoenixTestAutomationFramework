package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.utils.ConfigManager.*;

public class CountAPITest 
{
	@Test
	public void verifyCountAPIResponse()
	{
		Header header= new Header("Authorization", getToken(FD));
		
		given()
			.baseUri(getProperty("BASE_URI"))
			.contentType(ContentType.JSON)
			.header(header)
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.get("/dashboard/count")
		.then()
			.log().all()
			.statusCode(200)
			.body("message", equalTo("Success"))
			.time(lessThan(1500L))
			.body("data", notNullValue())
			.body("data.size()", equalTo(3))
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label", everyItem(not(blankOrNullString())))
			.body("data.key", containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
			.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
	}
	
	@Test
	public void countAPITest_MissingAuthToken()
	{
		given()
			.baseUri(getProperty("BASE_URI"))
			.contentType(ContentType.JSON)
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.get("/dashboard/count")
		.then()
			.log().all()
			.statusCode(401);
	}
}
