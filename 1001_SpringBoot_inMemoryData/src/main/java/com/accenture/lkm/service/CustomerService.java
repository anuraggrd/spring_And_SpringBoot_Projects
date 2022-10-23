package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.dao.CustomerDAO;
import com.accenture.lkm.dto.CustomerModel;
import com.accenture.lkm.entity.Customer;

@Service

public class CustomerService {
	
	@Autowired
	private CustomerDAO repo;
	
	
	
	public CustomerModel addCustomer(CustomerModel cusM)
	{
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
		CustomerModel customerModel = new CustomerModel();
		Customer customerEntity = repo.findById(id);
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
	
	//@org.springframework.transaction.annotation.Transactional
	// Transactional is use for rollback the transaction 
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

}
