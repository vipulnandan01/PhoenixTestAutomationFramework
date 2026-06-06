package com.api.tests;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MasterAPITest 
{
	@Test
	public void masterAPITest() {
		given()
			.baseUri(getProperty("BASE_URI"))
			.contentType("")
			.header("Authorization",getToken(FD))
			.log().all()
		.when()
			.post("master")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1000L))
			.body("message", equalTo("Success"))
			.body("data", notNullValue())
			.body("data", hasKey("mst_oem"))
			.body("data", hasKey("mst_model"))
			.body("$", hasKey("message"))
			.body("$", hasKey("data"))
			.body("data.mst_oem.size()", equalTo(2))
			.body("data.mst_model.size()", greaterThan(0))
			.body("data.mst_oem.id", everyItem(notNullValue()))
			.body("data.mst_oem.name", everyItem(notNullValue()))
			.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	@Test
	public void invalidTokenMasterAPITest()
	{
		given()
			.baseUri(getProperty("BASE_URI"))
			.contentType("")
			.header("Authorization","")
			.log().all()
		.when()
			.post("master")
		.then()
			.log().all()
			.statusCode(401);
	}
}
