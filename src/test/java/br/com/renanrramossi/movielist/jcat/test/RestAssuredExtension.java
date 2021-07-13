package br.com.renanrramossi.movielist.jcat.test;

import java.net.URI;
import java.net.URISyntaxException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseOptions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtension {
	private static final String BASE_URI = "http://localhost:8080/api";
	private static final String BASE_PATH = "/producers";

	private static final String URI = BASE_URI + BASE_PATH;

	public static RequestSpecification request;

	public RestAssuredExtension() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		request = RestAssured.given()
				.spec(builder.setBaseUri(BASE_URI).setBasePath(BASE_PATH).setContentType(ContentType.JSON).build());
	}

	public ResponseOptions<Response> get() {
		try {
			return request.get(new URI(URI));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
