package com.onlinebankingsystem.springproject.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Transaction;
import com.onlinebankingsystem.springproject.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transactionRepository;
	
	public Transaction saveTransaction(Transaction t) {
		Transaction obj = transactionRepository.save(t);
		return obj;
	}
	public List<Transaction> findAllTransactionsByAccountNumber(Account sourceAccountNumber) {
		List<Transaction> translist = transactionRepository.findAllTransactionsBySourceAccountNumber(sourceAccountNumber);
		return translist;
	}
	public List<Transaction> findTransactionStatementByAccount(Account sourceAccountNumber, Date startDate, Date endDate){
		return transactionRepository.findTransactionByDate(sourceAccountNumber, startDate, endDate);
	}
	public List<Transaction> findTransactionSummaryByAccount(Account sourceAccountNumber){
		return transactionRepository.findLatestByAccountNumber(sourceAccountNumber);
	}
	public List<Transaction> findTransactionSummaryByCustomerID(Long customerID){
		return transactionRepository.findRecentTransactions(customerID);
	}
	
}
