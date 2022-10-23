package com.curd.udemy.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerModel {
	
	private String name;
	private long customerId;
	private double salary;
	@JsonFormat(pattern="dd-MMM-yyyy")
	private Date joiningDate;

	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
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

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "CustomerModel [name=" + name + ", customerId=" + customerId + ", salary=" + salary + ", joiningDate="
				+ joiningDate + "]";
	}

	
}
