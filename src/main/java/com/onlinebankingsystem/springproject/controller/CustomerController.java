package com.onlinebankingsystem.springproject.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.repository.CustomerRepository;
import com.onlinebankingsystem.springproject.service.CustomerService;

@RestController
@CrossOrigin("http://localhost:3000")
@Validated
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping("/display/{cid}")
	public List<Account> displayAccounts(@PathVariable("cid")Integer cid) {
		List<Account> alist;
		alist = customerService.findCustomerByCustomerID(cid).getAccounts();
		return alist;
	}
	
	@PostMapping("/insert")
	public String insertCustomer(@RequestBody @Valid Customer c) {
		try {
			Customer obj = customerService.saveCustomer(c);
			if (obj!=null) {
				return "Save Successful!";
			}
			else return "Insert Table Failed!";
		}catch(Exception e) {
			return e.toString();
		}
	}
	@PostMapping("/login")
	public ResponseEntity<Object> loginCustomer(@RequestBody Map<String,Object> credentials){
		String password = (String) credentials.get("password");
		String emailID = (String) credentials.get("emailID");
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		Customer obj = customerService.findCustomerByEmail(emailID);
		if(obj==null) {
			responseText = "Customer Not Found";
			httpresult = HttpStatus.NOT_FOUND;
		}
		else if (!obj.getPassword().contentEquals(password)) {
			responseText = "Incorrect Password";
			httpresult = HttpStatus.UNAUTHORIZED;
		}
		else {
			responseText = "Login Successful";
		}
		result.put("obj", obj);
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}
}
