package com.onlinebankingsystem.springproject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.onlinebankingsystem.springproject.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	public Account findByAccountNumber(long accountNumber);
	public int existsByAccountNumber(long accountNumber);
	@Modifying
	@Transactional
	@Query("update Account account set account.accountBalance=account.accountBalance-?1 where account.accountNumber=?2" )
	public int withdraw(double amount,long accountno);
	
	@Modifying
	@Transactional
	@Query("update Account account set account.accountBalance=account.accountBalance+?1 where account.accountNumber=?2" )
	public int deposit(double amount,long accountno);
}
