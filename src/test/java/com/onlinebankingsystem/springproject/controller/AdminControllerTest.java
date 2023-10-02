package com.onlinebankingsystem.springproject.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Admin;
import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.model.Transaction;
import com.onlinebankingsystem.springproject.service.AdminService;
import com.onlinebankingsystem.springproject.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
class AdminControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private AdminService adminService;
    @MockBean
    private CustomerService customerService;

    private Customer c;
    private Customer c2;
    private Account a;
    private Transaction t;
    private Admin admin;

    @BeforeEach
    void setUp() {
        c = new Customer();
        c.setCustomerID(1L);
        c.setEmailID("cust@gmail.com");
        c.setPassword("cust123");
        c.setDateOfBirth(Date.valueOf("1995-02-02"));
        c.setPhoneNumber(1111111111);
        c.setPin(1234);
        c.setFirstName("John");
        c.setLastName("Doe");
        c.setActiveStatus(true);
        c.setResidentAddress("Bengaluru");

        c2 = new Customer();
        c2.setCustomerID(2L);
        c2.setEmailID("cust2@gmail.com");
        c2.setPassword("cust123");
        c2.setDateOfBirth(Date.valueOf("1999-02-02"));
        c2.setPhoneNumber(1112111111);
        c2.setPin(1134);
        c2.setFirstName("Johny");
        c2.setLastName("Poe");
        c2.setActiveStatus(true);
        c2.setResidentAddress("Bengaluru");

        a = new Account();
        a.setAccountBalance(0.0);
        a.setAccountNumber(1L);
        a.setAccountType("Savings Account");
        a.setCreditCardReq(false);
        a.setDebitCardReq(false);
        a.setOpenDate(Date.valueOf("2023-09-16"));
        a.setCustomerID(c);

        t = new Transaction();
        t.setTimestamp(new Timestamp(System.currentTimeMillis()));
        t.setSourceAccountNumber(a);
        t.setReceiverAccountNumber(a);
        t.setTransactionID(1L);
        t.setTransactionAmount(12.00);
        t.setTransactionType("deposit");
        admin = new Admin();
        admin.setAdminID(12L);
        admin.setPassword("123456");
        admin.setEmailID("admin@mail.com");
        // this.adminService=new AdminServiceImpl(adminRepository,customerRepository);
    }

    @Test
    void createAdminAccount() throws Exception{
        ResponseEntity<Object>res=new ResponseEntity<>(admin, HttpStatus.OK);
        when(adminService.createAdminAccount(any())).thenReturn(res);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/createAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(admin));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void loginAdmin() throws Exception {
        HashMap<String, Object> hs = new HashMap<>();
        hs.put("response", "Login Successful");

        ResponseEntity<Object> res = new ResponseEntity<>(hs, HttpStatus.OK);
        when(adminService.loginAdmin(any())).thenReturn(res);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/loginAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(admin));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void getUnverifiedCustomers() throws Exception {
        ResponseEntity<Object> res = new ResponseEntity<>(c, HttpStatus.OK);

        when(adminService.getUnverifiedCustomers()).thenReturn(res);
        MvcResult res1 = mockMvc.perform(MockMvcRequestBuilders
                        .get("/admin/getUnverifiedCustomers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void verifyCustomerAccount() throws Exception{
        HashMap<String, Object> hs = new HashMap<>();
        hs.put("response", "Account Found");
        HashMap<String,Object>credentials=new HashMap<>();
        credentials.put("account",a);
        ResponseEntity<Object>res=new ResponseEntity<>(hs, HttpStatus.OK);
        when(adminService.verifyCustomerAccount(any())).thenReturn(res);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/verifyCustomerAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(credentials));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());

    }
}