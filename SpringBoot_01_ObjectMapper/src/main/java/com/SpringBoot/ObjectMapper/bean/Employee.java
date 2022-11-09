package com.SpringBoot.ObjectMapper.bean;

public class Employee {

	private String name;
	private String city;
	private String designation;
	private String department;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, String city, String designation, String department) {
		super();
		this.name = name;
		this.city = city;
		this.designation = designation;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", city=" + city + ", designation=" + designation + ", department="
				+ department + "]";
	}

}
