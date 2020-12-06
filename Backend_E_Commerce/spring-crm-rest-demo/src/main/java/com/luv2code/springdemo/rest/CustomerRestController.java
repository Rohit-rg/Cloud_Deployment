package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	
	@Autowired 
	private CustomerService customerservice;
	
	@GetMapping("/customers")
	public List<Customer> getcustomers(){
		
		return customerservice.getCustomers();	
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getcustomer(@PathVariable int customerId)
	{
		
		Customer customer = customerservice.getCustomer(customerId);
		if(customer == null)
		{
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		return customer;
	}
	
	@PostMapping("/customers")
	public Customer addcustomer(@RequestBody Customer theCustomer)
	{
		theCustomer.setId(0);
		
		customerservice.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		customerservice.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId)
	{
	Customer customer = customerservice.getCustomer(customerId);
	
	if(customer == null)
	{
		throw new CustomerNotFoundException("Customer id not found - " + customerId);
	}
	
	customerservice.deleteCustomer(customerId);
	return "Deleted customer id - " + customerId ;
	}
}
