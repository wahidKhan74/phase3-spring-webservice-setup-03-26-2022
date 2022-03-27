package com.simplilearn.webservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webservice.entity.Product;

@RestController
public class ProductController {

	List<Product> products = new ArrayList<>();

	// get one product by id
	@GetMapping("/product/{id}")
	public Product getOne(@PathVariable(value = "id") int id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	// get one product by name
	@GetMapping("/product")
	public Product getOne(@RequestParam(value = "name") String name) {
		for (Product product : products) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	// get one product by name
	@GetMapping("/product/search")
	public Product searchOne(@RequestParam(value = "name") String name) {
		for (Product product : products) {
			if (product.getName().contains(name)) {
				return product;
			}
		}
		return null;
	}

	// get all products
	@GetMapping("/products")
	public List<Product> getProducts() {
		if (products.isEmpty()) {
			addDefaults();
		}
		return products;
	}

	// add product
	@PostMapping("/products")
	public List<Product> addOne(@RequestBody Product product) {
		products.add(product);
		return products;
	}

	// update product
	@PutMapping("/products")
	public Product updateOne(@RequestBody Product product) {
		for (int index = 0; index < products.size(); index++) {
			if (product.getId() == products.get(index).getId()) {
				// set : replace user product
				products.set(index, product);
				return product;
			}
		}
		return null;
	}

	// delete product
	@DeleteMapping("/products/{id}")
	public List<Product> deleteOne(@PathVariable(value = "id") int id) {
		for (int index = 0; index < products.size(); index++) {
			if (id == products.get(index).getId()) {
				// set : replace user product
				products.remove(index);
				return products;
			}
		}
		return null;
	}

	// add default products
	private void addDefaults() {
		products.add(new Product(10001, "HP 10012RED Model laptop", 993.994, "It is a laptop", true, new Date()));
		products.add(
				new Product(10002, "Apple mac book 9345MSLV series", 89993.994, "It is a laptop", true, new Date()));
		products.add(
				new Product(10003, "Dell slim book 98456ERSD series", 7723.994, "It is a laptop", false, new Date()));
		products.add(
				new Product(10004, "Lenovo slim book QURW954756 model", 3723.994, "It is a laptop", false, new Date()));
	}
}
