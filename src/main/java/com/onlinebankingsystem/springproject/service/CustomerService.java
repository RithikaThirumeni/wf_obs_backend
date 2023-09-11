package com.onlinebankingsystem.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public Customer saveCustomer(Customer c) {
		Customer obj=customerRepository.save(c);
		return obj;
	}
	public Customer findCustomerByEmail(String emailID) {
		Customer obj = customerRepository.findByEmailID(emailID);
		return obj;
	}
}
