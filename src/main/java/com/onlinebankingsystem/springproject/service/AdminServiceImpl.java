package com.onlinebankingsystem.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinebankingsystem.springproject.exception.AdminNotFoundException;
import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Admin;
import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.repository.AccountRepository;
import com.onlinebankingsystem.springproject.repository.AdminRepository;
import com.onlinebankingsystem.springproject.repository.CustomerRepository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.ValidationException;


@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    AccountRepository accountRepository;


    @Override
	public ResponseEntity<Object> createAdminAccount(Admin admin) throws Exception {
        Admin newAdmin = adminRepository.save(admin);
        HashMap<String,Object> res = new HashMap<>();
        res.put("responseText","Admin added");
        res.put("obj", newAdmin);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @Override
	public ResponseEntity<Object> createAccountForUser(HashMap<String,Object> details) throws Exception {
    	Customer c = new Customer();
    	int cid = (int) details.get("customerID");
    	c=customerRepository.findByCustomerID((long)cid);
    	Account a = new Account();
    	a.setCustomerID(c);
    	a.setAccountBalance((double)details.get("accountBalance"));
    	a.setAccountType((String)details.get("accountType"));
    	a.setActiveStatus(false);
    	a.setCreditCardReq((boolean)details.get("creditCardReq"));
    	a.setDebitCardReq((boolean)details.get("debitCardReq"));
    	a.setOpenDate(Date.valueOf((String)details.get("openDate")));
    	accountRepository.save(a);
    	HashMap<String,Object> res = new HashMap<>();
    	res.put("obj", a);
    	res.put("responseText", "created new account");
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
	public ResponseEntity<Object> loginAdmin(HashMap<String,Object> credentials) throws Exception {
        HashMap<String,Object> res = new HashMap<>();
        String email = (String) credentials.get("emailID");
        String pwd = (String) credentials.get("password");
        String responseText = "";
        HttpStatus httpres = HttpStatus.OK;
        Admin admin = adminRepository.findByEmailID(email);
        if(admin == null){
            throw new AdminNotFoundException();
            
        }
        else if(admin.getEmailID().contentEquals(email) && !admin.getPassword().contentEquals(pwd)) {
            responseText = "Incorrect Password";
            res.put("obj",0);
            httpres = HttpStatus.OK;
        }
        else {
            responseText = "Login Successful";
            res.put("obj",admin);
        }
        res.put("responseText",responseText);
        return new ResponseEntity<>(res, httpres);
    }
    @Override
	public ResponseEntity<Object> getUnverifiedCustomers() throws Exception {
        List<Customer> customers = customerRepository.findByUnverifiedCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    @Override
	public ResponseEntity<Object> verifyCustomer(HashMap<String,Object> details) throws Exception {
        try {
			HashMap<String,Object> res = new HashMap<>();
			String responseText = "";
			HttpStatus httpres = HttpStatus.OK;
			Long id = Long.parseLong(details.get("customerID").toString());
			Customer customer = customerRepository.findByCustomerID(id);
			if(customer == null) {
			    res.put("responseText","No such customer exists.");
			    res.put("obj", 0);
			    httpres = HttpStatus.OK;
			}
			else {
				if (customer.isActiveStatus()==true) {
					customer.setActiveStatus(false);
					responseText="Customer Disabled.";
				}
				else {
					customer.setActiveStatus(true);
					responseText="Customer Enabled.";
				}
			    Customer newCustomer = customerRepository.save(customer);
			    
			    res.put("obj",newCustomer);
			    res.put("responseText",responseText);
			}
			return new ResponseEntity<>(res,httpres);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ValidationException();
		}
    }
    @Override
	public ResponseEntity<Object> verifyCustomerAccount(HashMap<String,Object> details) throws Exception {
        try {
			HashMap<String,Object> res = new HashMap<>();
			String responseText = "";
			HttpStatus httpres = HttpStatus.OK;
			Long ano = Long.parseLong(details.get("accountNumber").toString());
			Account account = accountRepository.findByAccountNumber(ano);
			if(account == null) {
			    res.put("responseText","No such account exists.");
			    res.put("obj", 0);
			    httpres = HttpStatus.OK;
			}
			else {
				if (account.isActiveStatus()==true) {
					account.setActiveStatus(false);
					responseText="Account Disabled.";
				}
				else {
					account.setActiveStatus(true);
					responseText="Account Enabled.";
				}
			    Account newAccount = accountRepository.save(account);
			    res.put("obj",newAccount);
			    res.put("responseText",responseText);
			}
			return new ResponseEntity<>(res,httpres);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ValidationException();
		}
    }
}
