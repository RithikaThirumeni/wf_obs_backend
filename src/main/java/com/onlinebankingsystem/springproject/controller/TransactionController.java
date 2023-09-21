package com.onlinebankingsystem.springproject.controller;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingsystem.springproject.service.AccountService;
import com.onlinebankingsystem.springproject.service.TransactionService;
import com.onlinebankingsystem.springproject.model.Transaction;
import com.onlinebankingsystem.springproject.model.Account;

@RestController
@CrossOrigin("http://localhost:3000")
@Validated
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/transactions/{srcaccno}")
	public ResponseEntity<Object> displayTransactions(@PathVariable(value="srcaccno")Long srcaccno) {
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		Account srcaccount = accountService.findAccountByAccountNumber(srcaccno);
		if (srcaccount==null) {
			responseText = "Account Not Found";
			result.put("obj", 0);			
			result.put("responseText", responseText);
			return new ResponseEntity<>(result, httpresult);
		}
		List<Transaction> translist = transactionService.findAllTransactionsByAccountNumber(srcaccount);
		result.put("obj", translist);
		responseText="sucessfully retrieved balance";
		
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}
	@GetMapping("/accountstatement/{sourceAccountNumber}/{startDate}/{endDate}")
	public ResponseEntity<Object> accountStatement(@PathVariable(value="sourceAccountNumber")Long sourceAccountNumber,
			@PathVariable(value="startDate")String startDate, @PathVariable(value="endDate")String endDate) {
		
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		Account sourceAccount = accountService.findAccountByAccountNumber(sourceAccountNumber);
		if (sourceAccount==null) {
			responseText = "Account Not Found";
			result.put("obj", 0);			
			result.put("responseText", responseText);
			return new ResponseEntity<>(result, httpresult);
		}
		List<Transaction> translist = transactionService.findTransactionStatementByAccount(sourceAccount, Date.valueOf(startDate), Date.valueOf(endDate));
		result.put("obj", translist);
		responseText="sucessfully retrieved statement";
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}
	@GetMapping("/accountsummary/{sourceAccountNumber}")
	public ResponseEntity<Object> accountSummary(@PathVariable(value="sourceAccountNumber")Long sourceAccountNumber) {
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		Account sourceAccount = accountService.findAccountByAccountNumber(sourceAccountNumber);
		if (sourceAccount==null) {
			responseText = "Account Not Found";
			result.put("obj", null);			
			result.put("responseText", responseText);
			return new ResponseEntity<>(result, httpresult);
		}
		List<Transaction> translist = transactionService.findTransactionSummaryByAccount(sourceAccount)
				.stream().limit(5).collect(Collectors.toList());
		result.put("obj", translist);
		responseText="sucessfully retrieved summary";
		
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}
//	@GetMapping("/transactionsummary/{customerID}")
//	public ResponseEntity<Object> transactionSummary(@PathVariable(value="customerID")Long customerID) {
//		HttpStatus httpresult = HttpStatus.OK;
//		String responseText;
//		HashMap<String,Object> result = new HashMap<>();
//		Account sourceAccount = accountService.findAccountByAccountNumber(sourceAccountNumber);
//		List<Transaction> translist = transactionService.findTransactionSummaryByAccount(sourceAccount)
//				.stream().limit(5).collect(Collectors.toList());
//		result.put("obj", translist);
//		responseText="sucessfully retrieved summary";
//		
//		result.put("responseText", responseText);
//		return new ResponseEntity<>(result, httpresult);
//	} 
	
	
		
}