package com.springboot.udemy.Exception;



@SuppressWarnings("serial")
public class CustomerNotFound extends RuntimeException {
	
	@SuppressWarnings("unused")
	private String mesage;
	
	public CustomerNotFound()
	{
		
	}

	public CustomerNotFound(String mesage) {
		super();
		this.mesage = mesage;
	}
	
	

}
