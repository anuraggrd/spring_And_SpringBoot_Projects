package com.springboot.udemy.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.udemy.Exception.CustomerAlreadyExists;
import com.springboot.udemy.Exception.CustomerNotFound;
import com.springboot.udemy.dao.CustomerDAO;
import com.springboot.udemy.dto.CustomerModel;
import com.springboot.udemy.entity.Customer;

@Service

public class CustomerService {
	
	@Autowired
	private CustomerDAO repo;
	
	
	
	public CustomerModel addCustomer(CustomerModel cusM)
	{
		if(repo.findByNameAndCustomerCode(cusM.getName(), cusM.getCustCode()) != null)
		{
			throw new CustomerAlreadyExists();
		}
		Customer customerEntity = new Customer();
		BeanUtils.copyProperties(cusM, customerEntity);
		Customer cus = repo.save(customerEntity);

		CustomerModel customerModel = new CustomerModel();
		BeanUtils.copyProperties(cus, customerModel);
		
		System.out.println(customerModel);
		return customerModel;
	}
	
	
	
	public Collection<CustomerModel>getCustomerDetails()
	{
		Collection<Customer> customersEntity = repo.findAll();
		if(customersEntity == null)
		{
			throw new CustomerNotFound();
		}
		
		ArrayList<CustomerModel> list = new ArrayList<>();
		for (Customer customer : customersEntity) {
			CustomerModel  cusModel = new CustomerModel();
			BeanUtils.copyProperties(customer, cusModel);
			list.add(cusModel);
			
		}
		return list;
	}
	
	
	
	public CustomerModel getCustomerById(Integer id)
	{
		if(id ==0)
		{
			int a = 10/0;
		}
		CustomerModel customerModel = new CustomerModel();
		Customer customerEntity = repo.findById(id);
		if(customerEntity == null)
		{
			throw new CustomerNotFound();
		}
		BeanUtils.copyProperties(customerEntity, customerModel);
		return customerModel;
		
	}
	
	public CustomerModel updateCustomer(CustomerModel cusM)
	{
		CustomerModel customerM = null;
		Customer customerEntity = repo.findById(cusM.getCustomerId());
		if(customerEntity != null)
		{
			BeanUtils.copyProperties(cusM, customerEntity);
			//Customer customerEntity2 = repo.save(customerEntity);
			Customer customerEntity2 = repo.update(cusM.getCustomerId(),customerEntity);
			
			
			 customerM = new CustomerModel();
			 BeanUtils.copyProperties(customerEntity2, customerM);
		}
		return customerM;
		
	}
	
	 
	public CustomerModel deleteCustomerById(int id)
	{
		CustomerModel customerM = null;
		Customer customerEntity = repo.findById(id);
		if(customerEntity != null)
		{
			repo.deleteById(id);
			
			customerM = new CustomerModel();
			BeanUtils.copyProperties(customerEntity, customerM);
		}
		
		return customerM;
	}
	
	public CustomerModel findByNameAndCustomerCode(String name,String customerCode)
	{
		CustomerModel customerModel = new CustomerModel();
		Customer customer = repo.findByNameAndCustomerCode(name, customerCode);
		if(customer == null)
		{
			throw new CustomerNotFound();
		}
			BeanUtils.copyProperties(customer, customerModel);
		return customerModel;
	}
	
	
	

}
