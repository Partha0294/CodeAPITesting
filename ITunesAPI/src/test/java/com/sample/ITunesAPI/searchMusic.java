package com.sample.ITunesAPI;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class searchMusic {
	
	@Test
	public void searchLibrary()
	{
		//https://itunes.apple.com/search?term=jim+jones&country=ca
		
		RestAssured.baseURI = "https://itunes.apple.com";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		Response response = RestAssured.get("/search?term=jim+jones&country=ca");
		response.getStatusCode();
		
		// Assert for the count value
		int count = response.body().jsonPath().getInt("resultCount");
		System.out.println(count);
		
		Assert.assertEquals(count, 50);
		
		//Assert for the artistName
		
		String expectedArtistName = "David Yates";
		String actualArtistName = response.body().jsonPath().getJsonObject("results[2].artistName");
		
		Assert.assertEquals(actualArtistName, expectedArtistName);
	}
	
	@Test
	public void searchUser2() {
		
		// https://itunes.apple.com/search?term=jack+johnson&limit=25
		
		RestAssured.baseURI = "https://itunes.apple.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        
        Response  response = request.get("/search?term=jack+johnson&limit=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
	}
	
	@Test
	public void searchAppTitle() {
		
		// https://itunes.apple.com/search?term=yelp&country=us&entity=software
		
		RestAssured.baseURI = "https://itunes.apple.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        
        Response response = request.get("search?term=yelp");
        System.out.println(response.getStatusCode());
        
        // To fetch the result count
        int count = response.body().jsonPath().getInt("resultCount");
        System.out.println(count);
        
        Assert.assertEquals(count, 50);
    
	}
	
	@Test
	public void searchAppTitleName() {
		RestAssured.baseURI = "https://itunes.apple.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        
        Response response = request.get("search?term=yelp");
        System.out.println(response.getStatusCode());
        
        // To check the result count & TrackCensoredName
        int count = response.body().jsonPath().getInt("resultCount");
        System.out.println(count);
        
        Assert.assertEquals(count, 50);
        
        String expectedName = "Yelp";
        String actualName = response.body().jsonPath().getJsonObject("results[0].trackCensoredName");
        System.out.println(actualName);
        
        Assert.assertEquals(actualName, expectedName);
	}

}
