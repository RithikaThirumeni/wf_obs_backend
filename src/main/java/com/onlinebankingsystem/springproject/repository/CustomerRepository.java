package com.onlinebankingsystem.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebankingsystem.springproject.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public Customer findByEmailID(String emailID);
}
