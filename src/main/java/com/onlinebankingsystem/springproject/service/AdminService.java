package com.onlinebankingsystem.springproject.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Admin;

public interface AdminService {

	ResponseEntity<Object> createAdminAccount(Admin admin) throws Exception;

	ResponseEntity<Object> createAccountForUser(Account account, Long cid) throws Exception;

	ResponseEntity<Object> loginAdmin(HashMap<String, Object> credentials) throws Exception;

	ResponseEntity<Object> getUnverifiedCustomers() throws Exception;

	ResponseEntity<Object> verifyCustomer(HashMap<String, Object> details) throws Exception;

	ResponseEntity<Object> verifyCustomerAccount(HashMap<String, Object> details) throws Exception;

}