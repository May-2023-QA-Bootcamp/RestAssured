package configuration;

public interface IAPIPath {

	String BASE_URL = "https://reqres.in";
	
	String USERS_ENDPOINT = "/api/users";
	String USER_ENDPOINT = "/api/users/{userId}";
	
	String USERS_QUERY_PARAM = "page";
}
