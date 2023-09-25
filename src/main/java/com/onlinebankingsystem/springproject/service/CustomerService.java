package com.onlinebankingsystem.springproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public Customer findCustomerByCustomerID(Long customerID) {
		Customer obj = customerRepository.findByCustomerID(customerID);
		return obj;
	}
	public ResponseEntity<Object> getAllCustomers(Long id) throws Exception {
        List<Customer> customers = new ArrayList<Customer>();
        HttpStatus httpresult = HttpStatus.OK;
		HashMap<String,Object> result = new HashMap<>();
		
        if(id == null) {
        	result.put("responseText", "null id, get all");
            customerRepository.findAll().forEach(customers::add);
        }
        else {
        	Customer n = findCustomerByCustomerID(id);
        	if (n==null) {
        		result.put("responseText", "user not found");
        	}
        	else {
        		customers.add(n);
                result.put("responseText", "success");
        	}
        }
        result.put("obj", customers);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	public ResponseEntity<Object> updateCustomer(Long id, Customer customer) throws Exception {
		HashMap<String,Object> result = new HashMap<>();
        Customer foundCustomer = customerRepository.findByCustomerID(id);
        if(foundCustomer == null) {
        	result.put("responseText", "Such a Customer does not exist in our records.");
        	result.put("obj", 0);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setEmailID(customer.getEmailID());
        foundCustomer.setPhoneNumber(customer.getPhoneNumber());
        foundCustomer.setPassword(customer.getPassword());
        foundCustomer.setPin(customer.getPin());
        foundCustomer.setDateOfBirth(customer.getDateOfBirth());
        foundCustomer.setResidentAddress(customer.getResidentAddress());
        result.put("obj", customerRepository.save(foundCustomer));
        result.put("responseText", "Updated Customer");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
	public ResponseEntity<Object> resetPassword( HashMap<String,Object> newDetails) throws Exception {
		HashMap<String,Object> result = new HashMap<>();
        Customer customer = customerRepository.findByEmailID(newDetails.get("emailID").toString());
        Long otp = Long.parseLong(newDetails.get("otp").toString());
        String password = newDetails.get("password").toString();
        if(otp == 1234) {
            customer.setPassword(password);
            customerRepository.save(customer);
            result.put("obj", password);
            result.put("responseText", "Password Updated!");
            
        }
        else {
        	result.put("responseText", "Entered the wrong OTP, password not updated");
        	result.put("obj", password);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
