package com.onlinebankingsystem.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.repository.AccountRepository;
import com.onlinebankingsystem.springproject.repository.CustomerRepository;

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
	public int existsAccountByAccountNumber(long accountNumber) {
		Account obj = accountRepository.findByAccountNumber(accountNumber);
		if(obj!=null) return 1;
		else return 0;
	}
	public int withdrawFromAccount(double amount, long accountNumber) {
		if(amount>accountRepository.findByAccountNumber(accountNumber).getAccountBalance()) {
			return 0;
		}
		if(!accountRepository.findByAccountNumber(accountNumber).isActiveStatus()) {
			return 999;
		}
		int res = accountRepository.withdraw(amount, accountNumber);
		return res;
	}
	public int depositIntoAccount(double amount, long accountNumber) {
		if(accountRepository.findByAccountNumber(accountNumber).isActiveStatus()==false) {
			return 999;
		}
		int res = accountRepository.deposit(amount, accountNumber);
		return res;
	}
	public int fundTransfer(double amount, long sourceAccountNumber, long receiverAccountNumber) {
		if(!accountRepository.findByAccountNumber(sourceAccountNumber).isActiveStatus()) {
			return 999;
		}
		if(amount>accountRepository.findByAccountNumber(sourceAccountNumber).getAccountBalance()) {
			return 0;
		}
		accountRepository.withdraw(amount, sourceAccountNumber);
		int res = accountRepository.deposit(amount, receiverAccountNumber);
		return res;
		
	}
}
