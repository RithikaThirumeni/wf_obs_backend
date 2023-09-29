package com.onlinebankingsystem.springproject.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlinebankingsystem.springproject.model.Account;
import com.onlinebankingsystem.springproject.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	public List<Transaction> findAllTransactionsBySourceAccountNumber(Account sourceAccountNumber);
	
	@Query("select transaction from Transaction transaction "
			+ "where transaction.sourceAccountNumber=?1 AND transaction.transactionDate>=?2 AND transaction.transactionDate<=?3" )
	public List<Transaction> findTransactionByDate(Account sourceAccountNumber, Date startDate, Date endDate);
	
	@Query("select transaction from Transaction transaction where transaction.sourceAccountNumber=?1"
			+ " order by transaction.timestamp DESC")
	public List<Transaction> findLatestByAccountNumber(Account sourceAccountNumber);
	
	@Query("select t from Transaction t where t.sourceAccountNumber.customerID.customerID=?1 order by t.timestamp DESC")
	List<Transaction> findRecentTransactions(Long customerID);
}
