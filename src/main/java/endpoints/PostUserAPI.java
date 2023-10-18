package endpoints;

import configuration.BaseAPIStep;
import configuration.IAPIPath;
import dto.PostUser;
import io.restassured.http.ContentType;

public class PostUserAPI extends BaseAPIStep implements IAPIPath{

	/**
	 * postUserRequest use PostUser object
	 * @param user
	 * to make POST User API Request
	 */
	public void postUserRequest(PostUser user) {
		setBaseUrl(BASE_URL);
		setEndpoint(USERS_ENDPOINT);
		setContentType(ContentType.JSON);
		
		setPayload(user);
		getAllRequetLog();
		post();
		getAllResponseLog();
	}
}
