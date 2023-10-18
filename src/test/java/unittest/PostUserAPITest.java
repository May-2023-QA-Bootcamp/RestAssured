package unittest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import dto.PostUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostUserAPITest {

	String baseUri = "https://reqres.in";
	String userEndpoint = "/api/users";
	
	RequestSpecification requestSpecification;
	Response response;
	
	@Test
	@Disabled
	public void postUserStringBody() {
		requestSpecification = RestAssured.given();
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(userEndpoint);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.log().all();
		
		String body = "{\n"
				+ "    \"name\": \"morpheus\",\n"
				+ "    \"job\": \"leader\"\n"
				+ "}";
		requestSpecification.body(body);
		
		response = requestSpecification.post();
		
		response.prettyPrint();
		
		PostUser newUser = response.as(PostUser.class);
		
		System.out.println(newUser.getId());
	}
	
	@Test
	@Disabled
	public void postUserFileBody() {
		requestSpecification = RestAssured.given();
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(userEndpoint);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.log().all();
		
		File file = new File("src/test/resources/newUser.json");
		
		requestSpecification.body(file);
		
		response = requestSpecification.post();
		
		response.prettyPrint();
		
		PostUser newUser = response.as(PostUser.class);
		
		System.out.println(newUser.getCreatedAt());
	}
	
	@Test
	@Disabled
	public void postUserInputStreamBody() {
		requestSpecification = RestAssured.given();
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(userEndpoint);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.log().all();
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("newUser.json");
		
		requestSpecification.body(inputStream);
		
		response = requestSpecification.post();
		
		response.prettyPrint();
		
		PostUser newUser = response.as(PostUser.class);
		
		System.out.println(newUser.getJob());
	}
	
	@Test
	@Disabled
	public void postUserByteArrayBody() throws IOException {
		requestSpecification = RestAssured.given();
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(userEndpoint);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.log().all();
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("newUser.json");
		
		requestSpecification.body(inputStream.readAllBytes());
		
		response = requestSpecification.post();
		
		response.prettyPrint();
		
		PostUser newUser = response.as(PostUser.class);
		
		System.out.println(newUser.getJob());
	}
	
	@Test
	@Disabled
	public void postUserMapBody() {
		requestSpecification = RestAssured.given();
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(userEndpoint);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.log().all();
		
		Map<String, String>map = new HashMap<>();
		map.put("name", "Shain");
		map.put("job", "Bowler");
		
		requestSpecification.body(map);
		
		response = requestSpecification.post();
		
		response.prettyPrint();
		
		PostUser newUser = response.as(PostUser.class);
		
		System.out.println(newUser.getName());
	}
	
	@Test
	public void postUserObjectBody() {
		requestSpecification = RestAssured.given();
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(userEndpoint);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.log().all();
		
		PostUser requestPayload = new PostUser();
		requestPayload.setName("Rohit");
		requestPayload.setJob("Batsman");
		
		requestSpecification.body(requestPayload);
		
		response = requestSpecification.post();
		
		response.prettyPrint();
		
		PostUser newUser = response.as(PostUser.class);
		
		System.out.println(newUser.getCreatedAt());
		
		Assertions.assertEquals(requestPayload.getName(), newUser.getName());
	}
}
