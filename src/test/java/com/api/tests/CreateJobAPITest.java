package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest 
{
	
	@Test
	public void createJobAPITest()
	{
		CustomerAddress customerAddress= new CustomerAddress("104", "Ashish Green", "New Godown", "Opp to temple", "Kuthaganahalli", "560037", "India", "Karnataka");
		Customer customer= new Customer("Vipul", "Nandan", "7722345611", "", "techwithvipul02@gmail.com", "");
		CustomerProduct customerProduct= new CustomerProduct("2026-04-30T18:30:00.000Z", "24651599318637", "24651599318637", "24651599318637", "2026-04-30T18:30:00.000Z", 1, 1);
		Problems problems= new Problems(1, "Mobile Running slow");
		Problems[] problemsArray= new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
			.spec(SpecUtil.requestSpecWithAuth(FD, createJobPayload))
			.when()
				.post("job/create")
			.then()
				.spec(SpecUtil.responseSpec_OK());
	}
}
