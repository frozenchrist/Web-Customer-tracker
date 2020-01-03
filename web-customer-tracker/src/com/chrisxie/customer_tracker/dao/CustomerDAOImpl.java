package com.chrisxie.customer_tracker.dao;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisxie.customer_tracker.entity.Customer;


@Repository			//used specifically for DAO implementation class to talk to backend data source
public class CustomerDAOImpl implements CustomerDAO {

	
	@Autowired		//auto wires the sessionFactory component defined in Spring-mvc-crud-demo-servlet.xml
	private SessionFactory factory;
	
	@Override	
	public List<Customer> getCustomers() {

		Session session = factory.getCurrentSession();
		
		//read the table and sort by lastName
		Query<Customer> listCustomerQuery = session.createQuery("from Customer order by lastName",Customer.class);
		
		List<Customer> customerList = listCustomerQuery.getResultList();
		
	//another way to sort by last name	
		
//		customerList.sort( 
//				(customer1, customer2) -> 
//				customer1.getLastName().compareTo(customer2.getLastName())  
//				);
		
		return customerList;
		
	}

	@Override
	public void saveCustomers(Customer customer) {
		// TODO Auto-generated method stub
		
		Session session = factory.getCurrentSession();
		
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomerById(int id) {
		
		Session session = factory.getCurrentSession();
		
		Customer customer = session.get(Customer.class, id);
		
		return customer ;
	}

	@Override
	public void deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		
		Session session = factory.getCurrentSession();
	
		//Delete using HQL parameter binding
		Query deleteQuery = session.createQuery("delete from Customer where id=:customerId");
		
		deleteQuery.setParameter("customerId", id);
		
		deleteQuery.executeUpdate();
		
		
		//Delete using Java
//		Customer customerToDelete = session.get(Customer.class, id);		
//		
//		session.delete(customerToDelete);
		
	}

	@Override
	public List<Customer> searchCustomerByName(String searchName) {

		Session session = factory.getCurrentSession();
		
		Query searchNameQuery =
				session.createQuery("from Customer where lower(firstName) like :searchName or"
						+ " lower(lastName) like :searchName or lower(email) like :searchName");
		
		//Allow search for substrings, search is not case sensitive
		searchNameQuery.setParameter("searchName", "%" + searchName.toLowerCase() + "%");
		
		List<Customer> searchList = searchNameQuery.getResultList();
		
		return searchList;
	}

}
