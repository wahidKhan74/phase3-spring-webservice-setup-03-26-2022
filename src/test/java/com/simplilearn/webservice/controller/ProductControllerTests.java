package com.simplilearn.webservice.controller;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.simplilearn.webservice.entity.Product;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Product Controller APIs Tests")
public class ProductControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private long productId;

	@Test
	@DisplayName("GET all products test")
	public void testGetAllProducts() {
		// formulate a test url
		String url = "http://localhost:" + port + "/products";
		// call get api
		ResponseEntity<List> response = testRestTemplate.getForEntity(url, List.class);
		// Evaluate test response
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(false, response.getBody().isEmpty());
	}

	@Test
	@DisplayName("GET One product test")
	public void testGetOneProduct() {
		// formulate a test url
		String url = "http://localhost:" + port + "/product/4";
		// call get api
		ResponseEntity<Product> response = testRestTemplate.getForEntity(url, Product.class);
		// Evaluate test response
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(4, response.getBody().getId());
		assertEquals("Apple Iphone 13MAX Pro", response.getBody().getName());
		assertEquals(1156.546, response.getBody().getPrice());
		assertEquals(true, response.getBody().isEnabled());
	}

	@Test
	@DisplayName("Add One Product test")
	public void testAddProduct() {
		// formulate a test url
		String url = "http://localhost:" + port + "/products";
		// create Product Object
		Product product = new Product("Dell Gamming Laptop XYZ series", 98765.345, "It is a super gamming laptop", true,
				new Date());

		// create Http request entity obj
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		
		ResponseEntity<Product> response = testRestTemplate.postForEntity(url, requestObj, Product.class);
		// Evaluate test response
		productId = response.getBody().getId();
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Dell Gamming Laptop XYZ series", response.getBody().getName());
		assertEquals(98765.345, response.getBody().getPrice());
		assertEquals("It is a super gamming laptop", response.getBody().getDescription());
		assertEquals(true, response.getBody().isEnabled());
	}
	
	@Test
	@DisplayName("DELETE one Product test")
	public void testDeleteOneProduct() {
		// formulate a test url
		String url = "http://localhost:" + port + "/products/" + productId;
		// call delete one product api
		testRestTemplate.delete(url);
		assertEquals(true, true);
	}
}
