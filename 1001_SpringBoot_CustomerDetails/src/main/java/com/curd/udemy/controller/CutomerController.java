package com.curd.udemy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curd.udemy.dto.CustomerModel;
import com.curd.udemy.service.CustomerService;

@RestController
@RequestMapping("/customerDetailsapi")
public class CutomerController {
	
	@Autowired
	private CustomerService service;
	
	//add Customer
	@RequestMapping(value = "/addcustomer", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> addCustomer(@RequestBody CustomerModel customer)
	{
		CustomerModel customer2 = service.addCustomer(customer);
		System.out.println(customer2.getCustomerId());
		return new ResponseEntity<String>("Customer is added successfully with id :" + customer2.getCustomerId(),HttpStatus.CREATED);
	}
	
	
	//get All Customers 
	@RequestMapping(value = "/getCustDetails", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<CustomerModel>> getCustomerDetails()
	{
		Collection<CustomerModel> customerDetails = service.getCustomerDetails();
		
		return new ResponseEntity<Collection<CustomerModel>>(customerDetails,HttpStatus.OK);
	}
	
	// get Customer By Id
	@RequestMapping(value = "/getCustById/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("id")long id)
	{
		CustomerModel customer = service.getCustomerById(id);
		if(customer == null)
		{
			return new ResponseEntity<CustomerModel>(HttpStatus.NOT_FOUND);
		}else {
		
		return new ResponseEntity<CustomerModel>(customer,HttpStatus.OK);
		}
		
	}
	
	
	// update Cusomer
	
	@RequestMapping(value = "/updatecustomer", method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerModel> updateCustomer(@RequestBody CustomerModel customer)
	{
		CustomerModel updateCustomer = service.updateCustomer(customer);
		
		if(updateCustomer ==null)
		{
			return new ResponseEntity<CustomerModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else
		{
			return new ResponseEntity<CustomerModel>(customer,HttpStatus.OK);
		}
		
	}
	// delete Customer
	@RequestMapping(value = "/deleteCustomerById/{id}", method = RequestMethod.DELETE,produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id")long id)
	{
		CustomerModel customer = service.deleteCustomerById(id);
		if(customer == null)
		{
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
		
		return new ResponseEntity<String>("Remove Customer " +"{ CustomerId:"+ customer.getCustomerId() +" CustomerName: "+ customer.getName()+"}"  ,HttpStatus.OK);
		}
	}
	

}
