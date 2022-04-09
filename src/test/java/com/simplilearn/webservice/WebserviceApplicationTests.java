package com.simplilearn.webservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import com.simplilearn.webservice.controller.HomeController;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Webservice Application Tests")
class WebserviceApplicationTests {
	
	@Autowired
	private HomeController controller;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	@DisplayName("Test application context load")
	void contextLoads() {
		assertNotNull(controller);
	}
	
	@Test
	@DisplayName("Test server is running ?")
	void testForRunningServer(){
		
		String url = "http://localhost:"+port+"/";
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		String expected = "Welcome, to spring boot developement, Server is up and running !";
		String actual = response.getBody();
		assertEquals(expected, actual);
		assertEquals(200,  response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Test sample hello Api request")
	void testForHelloApis(){
		
		String url = "http://localhost:"+port+"/hello";
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		String expected = "Hello, everyone ! welcome to Webservice learning !";
		String actual = response.getBody();
		assertEquals(expected, actual);
		assertEquals(200,  response.getStatusCodeValue());
	}

}
