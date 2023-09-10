package com.onlinebankingsystem.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.service.CustomerService;

@RestController
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
}
