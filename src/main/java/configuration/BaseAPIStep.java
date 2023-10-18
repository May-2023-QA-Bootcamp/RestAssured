package configuration;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public abstract class BaseAPIStep {

	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	RequestSpecification requestSpecification;
	Response response;
	
	public RequestSpecification build() {
		logger.log(Level.INFO, "Building Request");
		return requestSpecification = RestAssured.given();
	}
	
	public RequestSpecification reset() {
		logger.log(Level.INFO, "Resetting Request");
		return requestSpecification = null;
	}
	
	public RequestSpecification setBaseUrl(String baseUri) {
		logger.log(Level.INFO, "Setting up base url : " + baseUri);
		return requestSpecification.baseUri(baseUri);
	}
	
	public RequestSpecification setEndpoint(String path) {
		logger.log(Level.INFO, "Setting up endpoint : " + path);
		return requestSpecification.basePath(path);
	}
	
	public RequestSpecification setQuaryParam(String paramName, Object paramValue) {
		return requestSpecification.queryParam(paramName, paramValue);
	}
	
	public RequestSpecification setPathParam(String paramName, String paramValue) {
		return requestSpecification.pathParam(paramName, paramValue);
	}
	
	public RequestSpecification setHeader(String headerName, String headerValue) {
		return requestSpecification.header(headerName, headerValue);
	}
	
	public RequestSpecification setContentType(ContentType contentType) {
		return requestSpecification.contentType(contentType);
	}
	
	public RequestSpecification setPayload(String payload) {
		return requestSpecification.body(payload);
	}
	
	public RequestSpecification setPayload(Map<String, ?> payload) {
		return requestSpecification.body(payload);
	}
	
	public RequestSpecification setPayload(Object payload) {
		return requestSpecification.body(payload);
	}
	
	public RequestSpecification setPayload(File payload) {
		return requestSpecification.body(payload);
	}
	
	public RequestSpecification setPayload(InputStream payload) {
		return requestSpecification.body(payload);
	}
	
	public RequestSpecification setPayload(byte[] payload) {
		return requestSpecification.body(payload);
	}
	
	public RequestSpecification getAllRequetLog() {
		return requestSpecification.log().all();
	}
	
	public ValidatableResponse getAllResponseLog() {
		return response.then().log().all();
	}
	
	public Response get() {
		logger.log(Level.INFO, "GET API Request");
		return response = requestSpecification.get();
	}
	
	public Response post() {
		return response = requestSpecification.post();
	}
	
	public Response put() {
		return response = requestSpecification.put();
	}
	
	public Response delete() {
		return response = requestSpecification.delete();
	}
	
	public Response get(String url) {
		return response = requestSpecification.get(url);
	}
	
	public Response post(String url) {
		return response = requestSpecification.post(url);
	}
	
	public Response put(String url) {
		return response = requestSpecification.put(url);
	}
	
	public Response delete(String url) {
		return response = requestSpecification.delete();
	}
	
	public Response getResponse() {
		return response;
	}
	
	public String getResponseString() {
		return response.asString();
	}
	
	public int getStatusCode() {
		return response.getStatusCode();
	}
	
	public void validateBodyJsonPathEqualTo(String path, Object expected) {
		getResponse().then().body(path, Matchers.equalTo(expected));
	}
	
	public void validateBodyJsonPathContainString(String path, String expected) {
		getResponse().then().body(path, Matchers.containsString(expected));
	}
}
