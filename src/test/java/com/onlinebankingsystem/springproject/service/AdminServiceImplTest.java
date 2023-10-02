package com.onlinebankingsystem.springproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onlinebankingsystem.springproject.exception.AccountNotFoundException;
import com.onlinebankingsystem.springproject.exception.AdminNotFoundException;
import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Admin;
import com.onlinebankingsystem.springproject.model.Customer;
import com.onlinebankingsystem.springproject.model.Transaction;
import com.onlinebankingsystem.springproject.repository.AccountRepository;
import com.onlinebankingsystem.springproject.repository.AdminRepository;
import com.onlinebankingsystem.springproject.repository.CustomerRepository;
import com.onlinebankingsystem.springproject.repository.TransactionRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminServiceImplTest {
    @Mock
    CustomerRepository customerRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    private AdminRepository adminRepository;


    private AdminService adminService;
    private Customer c;
    private Customer c2;
    private Account a;
    private Transaction t;
    private Admin admin;

    @BeforeEach
    void setUp() {
        c=new Customer();
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

        c2=new Customer();
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
        a.setAccountBalance(50.50);
        a.setAccountNumber(1L);
        a.setAccountType("Salary");
        a.setCreditCardReq(false);
        a.setDebitCardReq(false);
        a.setActiveStatus(false);
        a.setOpenDate(Date.valueOf("2023-09-16"));
        a.setCustomerID(c);

        t=new Transaction();
        t.setTimestamp(new Timestamp(System.currentTimeMillis()));
        t.setSourceAccountNumber(a);
        t.setReceiverAccountNumber(a);
        t.setTransactionID(1L);
        t.setTransactionAmount(12.00);
        t.setTransactionType("deposit");
        admin=new Admin();
        admin.setAdminID(12L);
        admin.setPassword("123456");
        admin.setEmailID("admin@mail.com");
        this.adminService=new AdminServiceImpl(adminRepository,customerRepository, accountRepository);

    }
    @Test
    void createAdminAccount() throws Exception{
        when(adminRepository.save(any())).thenReturn(admin);
        ResponseEntity<Object> res=adminService.createAdminAccount(admin);
        assert (res.getStatusCode()== HttpStatus.CREATED);

    }

    @Test
    void loginAdmin() throws Exception{
        HashMap<String,Object>credentials=new HashMap<>();
        credentials.put("emailID",admin.getEmailID());
        credentials.put("password","1234");
        when(adminRepository.findByEmailID(anyString())).thenReturn(null);
//        ResponseEntity<Object> res=adminService.loginAdmin(credentials);
        ResponseEntity<Object> res;
        assertThrows(AdminNotFoundException.class, ()->{adminService.loginAdmin(credentials);});
        when(adminRepository.findByEmailID(anyString())).thenReturn(admin);
        res=adminService.loginAdmin(credentials);
        HashMap<String,Object>hs= (HashMap<String, Object>) res.getBody();
        assert(hs.get("responseText")== "Incorrect Password");
        credentials.put("password",admin.getPassword());
        res=adminService.loginAdmin(credentials);
        hs= (HashMap<String, Object>) res.getBody();
        assert(hs.get("responseText")== "Login Successful");
    }

    @Test
    void getUnverifiedCustomers() throws Exception{
        List<Customer> customers =new ArrayList<>();
        when(customerRepository.findByUnverifiedCustomer()).thenReturn(customers);
        ResponseEntity<Object>res=adminService.getUnverifiedCustomers();
        assert (res.getStatusCode()==HttpStatus.OK);
    }

    @Test
    void verifyCustomerAccount() throws Exception{
        HashMap<String,Object>details=new HashMap<>();
        details.put("accountNumber",a.getAccountNumber());
        when(accountRepository.findByAccountNumber(anyLong())).thenReturn(null);
//        ResponseEntity<Object>res=adminService.verifyCustomerAccount(details);
        ResponseEntity<Object> res;
//        assertThrows(ValidationException.class, ()->{adminService.verifyCustomerAccount(details);});
        assert(adminService.verifyCustomerAccount(details).getStatusCode()==HttpStatus.OK);
//        assert (res.getStatusCode()==HttpStatus.OK);
        when(accountRepository.findByAccountNumber(anyLong())).thenReturn(a);
        when(accountRepository.save(any())).thenReturn(a);
        res=adminService.verifyCustomerAccount(details);
//        assertThrows(ValidationException.class, ()->{adminService.verifyCustomerAccount(details);});
        assert (res.getStatusCode()==HttpStatus.OK);


    }
}
