package com.simplilearn.webservice.exception;

public class ProductAlreadyExist extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProductAlreadyExist(String message){
		super(message);
	}
}
