package com.onlinebankingsystem.springproject.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebankingsystem.springproject.controller.CustomerController;
import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.repository.AccountRepository;
import com.onlinebankingsystem.springproject.repository.AdminRepository;
import com.onlinebankingsystem.springproject.repository.CustomerRepository;
import com.onlinebankingsystem.springproject.repository.TransactionRepository;
import com.onlinebankingsystem.springproject.service.AccountService;
import com.onlinebankingsystem.springproject.service.AccountServiceImpl;
import com.onlinebankingsystem.springproject.service.AdminService;
import com.onlinebankingsystem.springproject.service.AdminServiceImpl;
import com.onlinebankingsystem.springproject.service.CustomerService;
import com.onlinebankingsystem.springproject.service.CustomerServiceImpl;
import com.onlinebankingsystem.springproject.service.TransactionService;
import com.onlinebankingsystem.springproject.service.TransactionServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AccountControllerTest {
	@Autowired
	private MockMvc mvc;

	@InjectMocks
	private CustomerController customerController;
	
	@MockBean
	private AccountServiceImpl accountService;
	
	@MockBean
	private CustomerServiceImpl customerService;
	@MockBean
	private TransactionServiceImpl transactionService;
	@MockBean
	private AdminServiceImpl adminService;
	@MockBean
	private AdminRepository adminRepository;
	@MockBean
	private TransactionRepository transactionRepository;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private AccountRepository accountRepository;
	
	private static ObjectMapper mapper = new ObjectMapper();
	
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.initMocks(this);
//		mvc = standaloneSetup(customerController).build();
//	}
	
	@Test
	public void testCreateAccount() throws Exception {
		Customer c = new Customer();
		c.setCustomerID((long)1);
		c.setEmailID("cust@gmail.com");
		c.setPassword("cust123");
		c.setDateOfBirth(Date.valueOf("1995-02-02"));
		c.setPhoneNumber(1111111111);
		c.setPassword("abcd123");
		c.setPin(1234);
		c.setFirstName("John");
		c.setLastName("Doe");
		
		Account a = new Account();
		a.setAccountBalance(0.0);
		a.setAccountNumber(1);
		a.setAccountType("Savings");
		a.setCreditCardReq(false);
		a.setDebitCardReq(false);
		a.setOpenDate(Date.valueOf("2023-09-16"));
		a.setCustomerID(c);
		
		Mockito.when(accountService.saveAccount(ArgumentMatchers.any())).thenReturn(a);
		String json = mapper.writeValueAsString(a);
		mvc.perform(post("/createaccount").
				contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
}


