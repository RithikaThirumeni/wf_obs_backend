package com.onlinebankingsystem.springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlinebankingsystem.springproject.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public Customer findByEmailID(String emailID);
	public Customer findByCustomerID(Long customerID);
	@Query(value = "select * from customer where active_status=0 ", nativeQuery = true)
    List<Customer> findByUnverifiedCustomer();
}
