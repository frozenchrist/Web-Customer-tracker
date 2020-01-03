package com.chrisxie.customer_tracker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrisxie.customer_tracker.dao.CustomerDAO;
import com.chrisxie.customer_tracker.entity.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerDAO customerDao;
	
	
	@Override
	@Transactional         //takes care of begin transaction and commit
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		
		return customerDao.getCustomers();
		
	}


	@Override
	@Transactional   
	public void saveCustomers(Customer customer) {
		// TODO Auto-generated method stub
		
		customerDao.saveCustomers(customer);
		
	}


	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerDao.getCustomerById(id);
	}


	@Override
	@Transactional
	public void deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		
		customerDao.deleteCustomerById(id);
		
	}


	@Override
	@Transactional
	public List<Customer> searchCustomerByName(String searchName) {
		// TODO Auto-generated method stub
		return customerDao.searchCustomerByName(searchName);
	}

}
