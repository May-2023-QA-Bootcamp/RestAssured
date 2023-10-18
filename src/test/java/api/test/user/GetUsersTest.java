package api.test.user;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dto.Data;
import dto.Users;
import endpoints.GetUsersAPI;

public class GetUsersTest {

	static GetUsersAPI getUsers;
	
	@BeforeAll
	public static void init() {
		getUsers = new GetUsersAPI();
	}
	
	@BeforeEach
	public void testSetup() {
		getUsers.build();
	}
	
	@Test
	@DisplayName("TC_123 : Happy Path GetUsers API")
	public void getUsersHappyPath() {
		getUsers.getUsersCall();
		assertEquals(getUsers.getStatusCode(), 200);
		
		Users users = getUsers.getResponse().as(Users.class);
		List<Data> dataList = users.getData();
		
		assertEquals(2, users.getPage());
		assertTrue(dataList.get(2).getEmail().contains("@reqres.in"));
		
		assertEquals("Lindsay", users.getData().get(1).getFirst_name());
	}

	/**
	 * If there are no query param pass to get users
	 * API returns page 1 data
	 */
	@Test
	@DisplayName("TC_234 : No Query Param GetUsers API")
	public void validateNoQueryParam() {
		getUsers.noQueryParamGetUsers();
		assertEquals(200, getUsers.getStatusCode());
		
		getUsers.validateBodyJsonPathEqualTo("page", 1);
		getUsers.validateBodyJsonPathContainString("data.email[2]", "@reqres.in");
	}
	
	@AfterEach
	public void resetTest() {
		getUsers.reset();
	}
}
