package endpoints;

import configuration.BaseAPIStep;
import configuration.IAPIPath;

public class GetUsersAPI extends BaseAPIStep implements IAPIPath{
	
	/**
	 * Validating Happy Path testing for GetUsers API
	 */
	public void getUsersCall() {
		setBaseUrl(BASE_URL);
		setEndpoint(USERS_ENDPOINT);
		setQuaryParam(USERS_QUERY_PARAM, 2);
		getAllRequetLog();
		get();
		getAllResponseLog();
	}
	
	/**
	 * Validating GetUsers endpoint with query param 'page'
	 * If there are no query param pass to get users
	 * API returns page 1 data
	 */
	public void noQueryParamGetUsers() {
		setBaseUrl(BASE_URL);
		setEndpoint(USERS_ENDPOINT);
		getAllRequetLog();
		get();
		getAllResponseLog();
	}
}
