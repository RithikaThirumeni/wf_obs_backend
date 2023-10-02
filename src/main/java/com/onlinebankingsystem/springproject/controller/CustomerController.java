package com.onlinebankingsystem.springproject.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingsystem.springproject.exception.CustomerNotFoundException;
import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.service.CustomerService;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping("/customeraccounts/{cid}")
	public ResponseEntity<Object> displayAccounts(@PathVariable("cid")Long cid) {
		List<Account> alist;
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		if(customerService.findCustomerByCustomerID(cid)==null) {
			throw new CustomerNotFoundException();
		}
		alist = customerService.findCustomerByCustomerID(cid).getAccounts();
		responseText="accounts list by cid";
		result.put("obj", alist);
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}
	@GetMapping("/getAllCustomers")
    public ResponseEntity<Object> getAllCustomers(@RequestParam(required = false) Long id) throws Exception {
		return customerService.getAllCustomers(id);
    }
	
	@PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable(value = "id") Long id, @RequestBody @Valid Customer customer) throws Exception {
        return customerService.updateCustomer(id, customer);
    }
	@PutMapping("/resetPassword")
    public ResponseEntity<Object> resetPassword(@RequestBody HashMap<String,Object> newDetails) throws Exception {
        return customerService.resetPassword(newDetails);
    }
	
	@GetMapping("/hello")
	public String getMessage()
	{
		return "hello";
	}
	@PostMapping("/savecustomer")
	public ResponseEntity<Object> insertCustomer(@RequestBody @Valid Customer c) {
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		
		Customer obj = customerService.saveCustomer(c);
		if (obj!=null) {
			responseText = "Save Successful!";
			httpresult=HttpStatus.CREATED;
		}
		else {
			responseText = "Insert Table Failed!";
			httpresult=HttpStatus.FORBIDDEN;
		}
		
		result.put("obj", obj);
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
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
			throw new CustomerNotFoundException();
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
