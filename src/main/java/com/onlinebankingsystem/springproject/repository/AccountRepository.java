package com.onlinebankingsystem.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebankingsystem.springproject.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	public Account findByAccountNumber(long accountNumber);
}
