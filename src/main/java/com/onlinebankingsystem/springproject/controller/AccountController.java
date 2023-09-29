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
import com.onlinebankingsystem.springproject.service.TransactionServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
@Validated
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionServiceImpl transactionService;
	
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
	
	@GetMapping("/displaybalance/{accno}/{cid}")
	public ResponseEntity<Object> displayAccountBalance(@PathVariable("accno")long accno, @PathVariable("cid")long cid) {
		double balance;
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		if(accountService.findAccountByAccountNumber(accno).getCustomerID().getCustomerID()!=cid) {
			responseText = "Account cannot be accessed by "+cid+". Not the owner.";
			httpresult=HttpStatus.OK;
			result.put("obj", 0);
			result.put("responseText", responseText);
			return new ResponseEntity<>(result, httpresult);
		}
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
	
	@PostMapping("/withdraw/{cid}")
	public ResponseEntity<Object> withdrawTransaction(@RequestBody Map<String,Object> withdrawDetails, @PathVariable("cid")long cid) {
		double amount = (double) withdrawDetails.get("amount");
		int accountNumber = (int) withdrawDetails.get("accountNumber");
		
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		
		if(accountService.findAccountByAccountNumber(accountNumber).getCustomerID().getCustomerID()!=cid) {
			responseText = "Account cannot be accessed by "+cid+". Not the owner.";
			httpresult=HttpStatus.OK;
			result.put("obj", 0);
			result.put("responseText", responseText);
			return new ResponseEntity<>(result, httpresult);
		}
		int res= accountService.withdrawFromAccount(amount, accountNumber);
		if(res==999) {
			responseText = "Account is Disabled";
			httpresult=HttpStatus.OK;
		}
		else if (res==0) {
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
	@PostMapping("/deposit/{cid}")
	public ResponseEntity<Object> depositTransaction(@RequestBody Map<String,Object> depositDetails, @PathVariable("cid")long cid) {
		double amount = (double) depositDetails.get("amount");
		int accountNumber = (int) depositDetails.get("accountNumber");
		
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		if(accountService.findAccountByAccountNumber(accountNumber).getCustomerID().getCustomerID()!=cid) {
			responseText = "Account cannot be accessed by "+cid+". Not the owner.";
			httpresult=HttpStatus.OK;
			result.put("obj", 0);
			result.put("responseText", responseText);
			return new ResponseEntity<>(result, httpresult);
		}
		int res= accountService.depositIntoAccount(amount, accountNumber);
		if(res==999) {
			responseText = "Account is Disabled";
			httpresult=HttpStatus.OK;
		}
		else if (res==0) {
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
	
	@PostMapping("/fundtransfer/{cid}")
	public ResponseEntity<Object> fundTransfer(@RequestBody Map<String,Object> transferDetails, @PathVariable("cid")long cid) {
		double amount = (double) transferDetails.get("amount");
//		double amount = Double.parseDouble(d) ;
		int sourceAccountNumber = (int) transferDetails.get("sourceAccountNumber");
		int receiverAccountNumber = (int) transferDetails.get("receiverAccountNumber");
	
		HttpStatus httpresult = HttpStatus.OK;
		String responseText;
		HashMap<String,Object> result = new HashMap<>();
		if(accountService.findAccountByAccountNumber(sourceAccountNumber).getCustomerID().getCustomerID()!=cid) {
			responseText = "Source Account cannot be accessed by "+cid+". Not the owner.";
			httpresult=HttpStatus.OK;
			result.put("obj", 0);
			result.put("responseText", responseText);
			return new ResponseEntity<>(result, httpresult);
		}
		if(accountService.findAccountByAccountNumber(receiverAccountNumber).isActiveStatus()==false) {
			responseText = "Transaction Failed, receiver account is inactive!";
			httpresult=HttpStatus.OK;
			result.put("obj", 0);
			result.put("responseText", responseText);
			return new ResponseEntity<>(result, httpresult);
		}
		int res= accountService.fundTransfer(amount, sourceAccountNumber, receiverAccountNumber);
		if(res==999) {
			responseText = "Source Account is Disabled, Transaction Failed";
			httpresult=HttpStatus.OK;
		}
		else if (res==0) {
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
			t.setTransactionType("debit");
			t.setTransactionDate(Date.valueOf(LocalDate.now()));
			t.setTimestamp(ts);
			
			transactionService.saveTransaction(t);
			

			Transaction t1 = new Transaction();
			t1.setReceiverAccountNumber(accountService.findAccountByAccountNumber(sourceAccountNumber));
			t1.setSourceAccountNumber(accountService.findAccountByAccountNumber(receiverAccountNumber));
			t1.setTransactionAmount(amount);
			t1.setTransactionType("credit");
			t1.setTransactionDate(Date.valueOf(LocalDate.now()));
			t1.setTimestamp(ts);
			
			transactionService.saveTransaction(t1);		
			
		}
		result.put("obj", res);
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, httpresult);
	}

}
