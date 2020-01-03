package com.chrisxie.customer_tracker.service;

import java.util.List;

import com.chrisxie.customer_tracker.entity.Customer;

public interface CustomerService {

	
	public List<Customer> getCustomers();
	
	public void saveCustomers(Customer customer); 
	
	public Customer getCustomerById(int id);
	
	public void deleteCustomerById(int id);
	
	public List<Customer> searchCustomerByName(String searchName);
}
