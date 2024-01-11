package com.sample.ITunesAPI;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class lookUpMusic {
	
	@Test
	public void lookMusic() {
		
		// https://itunes.apple.com/lookup?upc=720642462928&entity=song
		
		RestAssured.baseURI = "https://itunes.apple.com";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		Response response = RestAssured.get("/lookup?upc=720642462928&entity=song");
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
		
		int track = response.body().jsonPath().getInt("resultCount");
		System.out.println(track);
		
		Assert.assertEquals(track, 36);
		
	}
	
	@Test
	public void lookMusicCountry() {
		RestAssured.baseURI = "https://itunes.apple.com";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		Response response = RestAssured.get("/lookup?upc=720642462928&entity=song");
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
		
		String expectedCountry = "USA";
		String actualCountry = response.body().jsonPath().getString("results[0].country");
		
		Assert.assertEquals(actualCountry, expectedCountry);
	}
	
	@Test
	public void lookMusicWrapperType() {
		RestAssured.baseURI = "https://itunes.apple.com";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		Response response = RestAssured.get("/lookup?upc=720642462928&entity=song");
		System.out.println(response.getStatusCode());
		
		String expectedType = "track";
		String actualType = response.body().jsonPath().getString("results[1].wrapperType");
		System.out.println(actualType);
		
		Assert.assertEquals(actualType, expectedType);
	}
	
	@Test
	public void lookMusicOneParameter() {
		RestAssured.baseURI = "https://itunes.apple.com";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		Response response = RestAssured.get("/lookup?upc=720642462928");
		System.out.println(response.getStatusCode());
		
		// Check with the result count
		int count = response.body().jsonPath().getInt("resultCount");
		System.out.println(count);
		
		Assert.assertEquals(count, 2);
		
		// Check with the collectionCensoredName
		String expectedName = "Weezer";
		String actualName = response.body().jsonPath().getJsonObject("results[1].collectionCensoredName");
		System.out.println(actualName);
		
		Assert.assertEquals(actualName, expectedName);
	}

}
