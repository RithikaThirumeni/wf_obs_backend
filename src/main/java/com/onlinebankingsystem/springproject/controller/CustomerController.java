package com.onlinebankingsystem.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.repository.CustomerRepository;
import com.onlinebankingsystem.springproject.service.CustomerService;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping("/display")
	public String welcome() {
		return "Welcome Page Spring Project";
	}
	
	@PostMapping("/insert")
	public String insertCustomer(@RequestBody Customer c) {
		Customer obj = customerService.saveCustomer(c);
		if (obj!=null) {
			return "Save Successful!";
		}
		else return "Insert Table Failed!";
//		return obj;
	}
	@PostMapping("/login")
	public Customer loginCustomer(@RequestBody JsonNode jsonNode){
		String password = jsonNode.get("password").asText();
		String emailID = jsonNode.get("emailID").asText();
//		return "welcome : "+emailID+", password: "+password;
		Customer obj = customerService.findCustomerByEmail(emailID);
		if (obj.getPassword().contentEquals(password)) {
			return obj;
		}
		else return null;
	}
}
