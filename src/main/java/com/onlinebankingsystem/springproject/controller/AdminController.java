package com.onlinebankingsystem.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onlinebankingsystem.springproject.model.Admin;
import com.onlinebankingsystem.springproject.service.AdminService;
import com.onlinebankingsystem.springproject.service.CustomerService;

import java.util.HashMap;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    CustomerService customerService;

    @PostMapping("/createAdmin")
    public ResponseEntity<Object> createAdminAccount(@RequestBody Admin admin) throws Exception {
        return adminService.createAdminAccount(admin);
    }
    @PostMapping("/loginAdmin")
    public ResponseEntity<Object> loginAdmin(@RequestBody HashMap<String,Object> credentials) throws Exception {
        return adminService.loginAdmin(credentials);
    }

    @GetMapping("/getUnverifiedCustomers")
    public ResponseEntity<Object> getUnverifiedCustomers() throws Exception {
        return adminService.getUnverifiedCustomers();
    }
    @PostMapping("/verifyCustomerAccount")
    public ResponseEntity<Object> verifyCustomerAccount(@RequestBody HashMap<String,Object> details) throws Exception {
        return adminService.verifyCustomerAccount(details);
    }
    @PostMapping("/verifyCustomer")
    public ResponseEntity<Object> verifyCustomer(@RequestBody HashMap<String,Object> details) throws Exception {
        return adminService.verifyCustomer(details);
    }
    //get all customers --> customer controller get mapping
    //toggle account status
    //verify customer
    //create account for user --> account controller post mapping
    
}