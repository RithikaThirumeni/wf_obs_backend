package com.onlinebankingsystem.springproject.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.onlinebankingsystem.springproject.model.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer c);

	Customer findCustomerByEmail(String emailID);

	Customer findCustomerByCustomerID(Long customerID);

	ResponseEntity<Object> getAllCustomers(Long id) throws Exception;

	ResponseEntity<Object> updateCustomer(Long id, Customer customer) throws Exception;

	ResponseEntity<Object> resetPassword(HashMap<String, Object> newDetails) throws Exception;

}