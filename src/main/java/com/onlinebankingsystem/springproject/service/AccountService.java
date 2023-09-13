package com.onlinebankingsystem.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	public Account saveAccount(Account a) {
		Account obj = accountRepository.save(a);
		return obj;
	}
	public Account findAccountByAccountNumber(long accountNumber) {
		Account obj = accountRepository.findByAccountNumber(accountNumber);
		return obj;
	}
}
