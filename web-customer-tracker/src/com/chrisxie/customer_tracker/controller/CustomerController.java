package com.chrisxie.customer_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.DispatcherServlet;

import com.chrisxie.customer_tracker.dao.CustomerDAO;
import com.chrisxie.customer_tracker.entity.Customer;
import com.chrisxie.customer_tracker.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model Model) {
		
		//get the customer list from the DAO
		List<Customer> customerList = customerService.getCustomers();
		
		//add the list into the model attribute to forward it to the view page
		Model.addAttribute("customerList", customerList);
		
		return "list-customer";
		
	}
	
	
	@GetMapping("/addCustomerForm")
	public String addCustomers(Model Model) {
		
		Customer newCustomer = new Customer();
		
		Model.addAttribute("customer", newCustomer);
		
		
		return "add-customer";
		
	}
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		
		customerService.saveCustomers(customer);
		
		return "redirect:/customer/list";
		
		 
	}
	
	
	@GetMapping("/updateCustomerForm")
	public String updateCustomer(@RequestParam("customerId")int id, Model model) {
		
		Customer customer = customerService.getCustomerById(id);
		
		model.addAttribute("customer", customer);
		
		return "add-customer";
		
	}
	
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomerById(id);
		
		
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("searchName") String searchName,
			Model model) {
		
		if (searchName == null || searchName.trim().length()<1) {
			
			return "redirect:/customer/list";
		}
		
		List<Customer> customerList = customerService.searchCustomerByName(searchName);
		
		model.addAttribute("customerList", customerList);
		
		return "list-customer";
	}
	
}
