package unittest;

import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import dto.Data;
import dto.Support;
import dto.Users;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUserAPITest {

	String baseUri = "https://reqres.in";
	String getUserEndpoint = "/api/users";
	
	@Test
	@Disabled
	public void simpleGetCall() {
		RestAssured.get("https://reqres.in/api/users/3").andReturn().prettyPrint();
	}
	
	@Test
	@Disabled
	public void simpleGetCallwithLogs() {
		RestAssured.given()
		.log().all()
		.when()
		.get("https://reqres.in/api/users/3")
		.then()
		.statusCode(200)
		.log().all();
		//.log().everything();
	}
	
	@Test
	@Disabled
	public void getCallwithBaseURIwithPathParam() {
		RestAssured.given()
		.baseUri("https://reqres.in")
		.basePath("/api/users/{id}")
		.header("Accept","*/*")
		.pathParams("id",5)
		.log().all()
		.when()
		.get()
		.then()
		.log().all()
		.statusCode(200);
	}
	
	@Test
	@Disabled
	public void getCallQueryParam() {
		RestAssured.given()
		.baseUri(baseUri)
		.basePath(getUserEndpoint)
		.queryParam("page", 1)
		.log().all()
		.when()
		.get()
		.then()
		.log().all();
	}
	
	@Test
	@Disabled
	public void getCallResponseValidation() {
		RestAssured.given()
		.baseUri(baseUri)
		.basePath(getUserEndpoint)
		.queryParam("page", 1)
		.log().all()
		.when()
		.get()
		.then()
		.log().all()
		.statusCode(200)
		.header("Content-Type", containsString("application/json"))
		.body("per_page", equalTo(6));
	}
	
	@Test
	@Disabled
	public void getCallResponseBodyValidation() {
		Response response = RestAssured.given()
		.baseUri(baseUri)
		.basePath(getUserEndpoint)
		.queryParam("page", 2)
		.log().all()
		.when()
		.get();
		
		response.prettyPrint();
		
		JsonPath jsonPath = JsonPath.from(response.asString());
		
		int perPageValue = jsonPath.getInt("per_page");
		
		List<String> list = jsonPath.getList("data");
		System.out.println(list);
		
		Assertions.assertEquals(perPageValue, list.size());
		
		System.out.println(jsonPath.getString("data.email[1]"));
		System.out.println(jsonPath.getString("data.avatar[3]"));
	}
	
	@Test
	@Disabled
	public void convertToPojoGetUser() {
		Response response = RestAssured.given()
			.baseUri(baseUri)
			.basePath(getUserEndpoint + "/{id}")
			.pathParam("id", "5")
			.log().all()
			.when()
			.get();
		
		response.prettyPrint();
		
		JsonPath jsonPath = new JsonPath(response.asString());
		//System.out.println(jsonPath.getString("data.firs_name"));
		
//		Data user = response.as(Data.class);
		
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		
//		Data user = mapper.convertValue(response.asString(), Data.class);
		
		Data user = jsonPath.getObject("data", Data.class);
		Support support = jsonPath.getObject("support", Support.class);
		
		System.out.println("FirstName : " + user.getFirst_name());
		System.out.println("Support Text : " + support.getText());
	}
	
	@Test
	@Disabled
	public void convertToPojoGetUsers() {
		Response response = RestAssured.given()
			.baseUri(baseUri)
			.basePath(getUserEndpoint)
			.queryParam("page", 1)
			.log().all()
			.when()
			.get();
		
		response.prettyPrint();
		
		//JsonPath jsonPath = JsonPath.from(response.asInputStream());
		//Users users = jsonPath.getObject("", Users.class);
		
		Users users = response.as(Users.class);
		
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		Users users = mapper.convertValue(response, Users.class);
		
		System.out.println(users.getPage());
		System.out.println(users.getData().get(2).getEmail());
	}
	
	@Test
	public void breakDownRequest_n_response() {
		RequestSpecification requestSpecification;
		Response response;
		
		requestSpecification = RestAssured.given();
		
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(getUserEndpoint);
		requestSpecification.queryParam("page", 1);
		requestSpecification.header("","");
		
		response = requestSpecification.get();
		
		response.prettyPrint();
		
		int status = response.getStatusCode();
		System.out.println(status);
		
		int total = response.path("total");
		System.out.println(total);
		Assertions.assertEquals(12, (int) response.path("total"));
	}
}
