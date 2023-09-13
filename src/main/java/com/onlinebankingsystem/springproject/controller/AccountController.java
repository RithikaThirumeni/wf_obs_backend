package com.onlinebankingsystem.springproject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.service.AccountService;
import com.onlinebankingsystem.springproject.service.CustomerService;

@RestController
@CrossOrigin("http://localhost:3000")
@Validated
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/createaccount")
	public String createAccount(@RequestBody @Valid Account a) {
		Account obj = accountService.saveAccount(a);
		if (obj!=null) {
			return "Account Created";
		}
		else return "Account Creation Failed";
	}

}
