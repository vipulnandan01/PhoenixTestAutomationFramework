package com.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.api.utils.ConfigManager.*;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;

public class SpecUtil 
{
	private SpecUtil()
	{
		
	}
	
	//GET--DELETE
	public static RequestSpecification requestSpec()
	{
		RequestSpecification requestSpecification= new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		
		return requestSpecification;
	}
	
	//POST-PUT-PATCH {BODY}
	public static RequestSpecification requestSpec(Object payload)
	{
		RequestSpecification requestSpecification= new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		
		return requestSpecification;
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role)
	{
		RequestSpecification requestSpecification= new RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		
		return requestSpecification;
	}
	
	public static ResponseSpecification responseSpec_OK()
	{
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(1000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_JSON(int statusCode)
	{
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
				.expectStatusCode(statusCode)
				.expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(1000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode)
	{
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
				.expectStatusCode(statusCode)
				.expectResponseTime(Matchers.lessThan(1000L))
				.log(LogDetail.ALL)
				.build();
		return responseSpecification;
	}
	
}
