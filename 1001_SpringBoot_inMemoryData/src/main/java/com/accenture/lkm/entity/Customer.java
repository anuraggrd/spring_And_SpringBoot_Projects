package com.accenture.lkm.entity;

public class Customer {
	
	
	private int customerId;
	private String name;
	private double salary;
	private String custCode;
	
	public Customer()
	{
		
	}

	

	public Customer(int customerId, String name, double salary, String custCode) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.salary = salary;
		this.custCode = custCode;
		
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	
}
