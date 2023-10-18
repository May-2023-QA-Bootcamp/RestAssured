package api.test.user;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dto.PostUser;
import endpoints.PostUserAPI;

public class PostUserTest {

	static PostUserAPI postUser;
	
	@BeforeAll
	public static void init() {
		postUser = new PostUserAPI();
	}
	
	@BeforeEach
	public void testSetup() {
		postUser.build();
	}
	
	@AfterEach
	public void testReset() {
		postUser.reset();
	}
	
	@Test
	@DisplayName("TC_321 : Validating POST User Happy Path")
	public void postUserValdiation() {
		PostUser requestUser = new PostUser();
		requestUser.setName("Soaib");
		requestUser.setJob("Fast Bowler");
		
		postUser.postUserRequest(requestUser);
		
		PostUser responseUser = postUser.getResponse().as(PostUser.class);
		
		assertEquals(201, postUser.getStatusCode());
		assertEquals(requestUser.getName(), responseUser.getName());
		assertFalse(requestUser.equals(responseUser));
		System.out.println(requestUser.hashCode());
		System.out.println(responseUser.hashCode());
	}
}
