package com.accenture.lkm.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.accenture.lkm.entity.Customer;

@Repository
public class CustomerDAO {
	
	static public Map<Integer,Customer>mapOfCustomer = new LinkedHashMap<>();
	static Integer count=1004;
	static
	{
		mapOfCustomer.put(1001,new Customer(1001,"Anurag",5000.00,"An01"));
		mapOfCustomer.put(1002,new Customer(1002,"Rohit",10000.00,"Ro02"));
		mapOfCustomer.put(1003,new Customer(1003,"Amit",15000.00,"Am03"));
	}
	
	
	public Customer save(Customer customer)
	{
		count++;
		customer.setCustomerId(count);
		mapOfCustomer.put(count, customer);
		return customer;
	}
	
	
	public Collection<Customer> findAll()
	{
		return mapOfCustomer.values();
	}
	
	public Customer findById(Integer id)
	{
		return mapOfCustomer.get(id);
	}
	
	public Customer update(int id,Customer customer)
	{
		
		
		mapOfCustomer.put(id, customer);
		return customer;
	}
	
	public void deleteById(Integer id)
	{
		mapOfCustomer.remove(id);
	}

}
