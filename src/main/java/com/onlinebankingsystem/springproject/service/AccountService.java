package com.onlinebankingsystem.springproject.service;

import com.onlinebankingsystem.springproject.model.Account;

public interface AccountService {

	Account saveAccount(Account a);

	Account findAccountByAccountNumber(long accountNumber);

	int existsAccountByAccountNumber(long accountNumber);

	int withdrawFromAccount(double amount, long accountNumber);

	int depositIntoAccount(double amount, long accountNumber);

	int fundTransfer(double amount, long sourceAccountNumber, long receiverAccountNumber);

}