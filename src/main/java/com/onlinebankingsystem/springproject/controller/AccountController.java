package com.onlinebankingsystem.springproject.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Transaction;
import com.onlinebankingsystem.springproject.service.AccountService;
import com.onlinebankingsystem.springproject.service.TransactionService;

@RestController
@CrossOrigin("http://localhost:3000")
@Validated
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/createaccount")
	public ResponseEntity<Object> createAccount(@RequestBody @Valid Account a) {
		String responseText="";
		HashMap<String,Object> result = new HashMap<>();
		HttpStatus httpresult = HttpStatus.OK;
		Account obj = accountService.saveAccount(a);
		if (obj!=null) {
			responseText = "Save Successful!";
			httpresult=HttpStatus.OK;
		}
		else {
			responseText = "Insert Table Failed!";
			httpresult=HttpStatus.OK;
		}
		result.put("obj", obj);
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}
	
	@GetMapping("/displaybalance/{accno}")
	public ResponseEntity<Object> displayAccountBalance(@PathVariable("accno")long accno) {
		double balance;
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		if(accountService.existsAccountByAccountNumber(accno)==1) {
			balance = accountService.findAccountByAccountNumber(accno).getAccountBalance();
			result.put("obj", balance);
			responseText="success";
			result.put("responseText", responseText);
		}
		else {
			responseText="account does not exist";
			result.put("obj", 0);
			result.put("responseText", responseText);
		}
		
		return new ResponseEntity<>(result, httpresult);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Object> withdrawTransaction(@RequestBody Map<String,Object> withdrawDetails) {
		double amount = (double) withdrawDetails.get("amount");
		int accountNumber = (int) withdrawDetails.get("accountNumber");
		
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();

		int res= accountService.withdrawFromAccount(amount, accountNumber);
		if (res==0) {
			responseText = "Insufficent Balance, failed to withdraw";
			httpresult=HttpStatus.OK;
		}
		else {
			responseText = "Updated balance!";
			httpresult=HttpStatus.OK;
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			Transaction t = new Transaction();
			t.setReceiverAccountNumber(accountService.findAccountByAccountNumber(accountNumber));
			t.setSourceAccountNumber(accountService.findAccountByAccountNumber(accountNumber));
			t.setTransactionAmount(amount);
			t.setTransactionType("withdraw");
			t.setTransactionDate(Date.valueOf(LocalDate.now()));
			t.setTimestamp(ts);
			
			transactionService.saveTransaction(t);
		}
		result.put("obj", res);
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}
	@PostMapping("/deposit")
	public ResponseEntity<Object> depositTransaction(@RequestBody Map<String,Object> depositDetails) {
		double amount = (double) depositDetails.get("amount");
		long accountNumber = (long) depositDetails.get("accountNumber");
		
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		int res= accountService.depositIntoAccount(amount, accountNumber);
		if (res!=1) {
			responseText = "Update failed";
			httpresult=HttpStatus.OK;
		}
		else {
			responseText = "Updated balance!";
			httpresult=HttpStatus.OK;
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			Transaction t = new Transaction();
			t.setReceiverAccountNumber(accountService.findAccountByAccountNumber(accountNumber));
			t.setSourceAccountNumber(accountService.findAccountByAccountNumber(accountNumber));
			t.setTransactionAmount(amount);
			t.setTransactionType("deposit");
			t.setTransactionDate(Date.valueOf(LocalDate.now()));
			t.setTimestamp(ts);
			
			transactionService.saveTransaction(t);
		}
		result.put("obj", res);
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}
	
	@PostMapping("/fundtransfer")
	public ResponseEntity<Object> findTransfer(@RequestBody Map<String,Object> transferDetails) {
		double amount = (double) transferDetails.get("amount");
//		double amount = Double.parseDouble(d) ;
		int sourceAccountNumber = (int) transferDetails.get("sourceAccountNumber");
		int receiverAccountNumber = (int) transferDetails.get("receiverAccountNumber");
	
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		int res= accountService.fundTransfer(amount, sourceAccountNumber, receiverAccountNumber);
		if (res==0) {
			responseText = "Transfer Failed, check balance and account number";
			httpresult=HttpStatus.OK;
		}
		else {
			responseText = "Fund Transfer Successful";
			httpresult=HttpStatus.OK;
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			Transaction t = new Transaction();
			t.setReceiverAccountNumber(accountService.findAccountByAccountNumber(receiverAccountNumber));
			t.setSourceAccountNumber(accountService.findAccountByAccountNumber(sourceAccountNumber));
			t.setTransactionAmount(amount);
			t.setTransactionType("transfer");
			t.setTransactionDate(Date.valueOf(LocalDate.now()));
			t.setTimestamp(ts);
			
			transactionService.saveTransaction(t);
		}
		result.put("obj", res);
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}

}
