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
		
        if(id == null)
            customerRepository.findAll().forEach(customers::add);
        else
            customers.add(customerRepository.findByCustomerID(id));

        if(customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        result.put("obj", customers);
        result.put("responseText", "success");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	public ResponseEntity<Object> updateCustomer(Long id, Customer customer) throws Exception {
        Customer foundCustomer = customerRepository.findByCustomerID(id);
        if(foundCustomer == null) {
            return new ResponseEntity<>("Such a Customer does not exist in our records.", HttpStatus.BAD_REQUEST);
        }
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setEmailID(customer.getEmailID());
        foundCustomer.setPhoneNumber(customer.getPhoneNumber());
        foundCustomer.setPassword(customer.getPassword());
        foundCustomer.setPin(customer.getPin());
        foundCustomer.setDateOfBirth(customer.getDateOfBirth());
        foundCustomer.setResidentAddress(customer.getResidentAddress());

        return new ResponseEntity<>(customerRepository.save(foundCustomer),HttpStatus.OK);
    }
}
