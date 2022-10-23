package com.curd.udemy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curd.udemy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

}
