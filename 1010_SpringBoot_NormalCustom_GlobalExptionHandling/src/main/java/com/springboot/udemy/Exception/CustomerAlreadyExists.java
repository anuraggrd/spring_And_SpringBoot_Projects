package com.springboot.udemy.Exception;



@SuppressWarnings("serial")
public class CustomerAlreadyExists extends RuntimeException {
	
	@SuppressWarnings("unused")
	private String mesage;
	
	public CustomerAlreadyExists()
	{
		
	}

	public CustomerAlreadyExists(String mesage) {
		super();
		this.mesage = mesage;
	}
	
	

}
