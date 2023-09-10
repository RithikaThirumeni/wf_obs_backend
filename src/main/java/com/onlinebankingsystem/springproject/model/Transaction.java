package com.onlinebankingsystem.springproject.model;

import java.math.BigDecimal;
import java.sql.Date;
//import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transactionID")
	private long TransactionID;
	
	@Column(name="transactionType")
	private String TransactionType;
	
	@Column(name="transactionAmount")
	private BigDecimal TransactionAmount;
	
	@Column(name="transactionDate")
	private Date TransactionDate;
	
//	@Column(name="timestamp")
//	private Timestamp timestamp;
	
	@ManyToOne
	@JoinColumn(name="sourceAccountNumber", referencedColumnName="accountNumber")
	private Account SourceAccountNumber;
	
	public BigDecimal getTransactionAmount() {
		return TransactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		TransactionAmount = transactionAmount;
	}

	public String getReceiverDescription() {
		return ReceiverDescription;
	}

	public void setReceiverDescription(String receiverDescription) {
		ReceiverDescription = receiverDescription;
	}

	@Column(name="receiverDescription")
	private String ReceiverDescription;

	public long getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(long transactionID) {
		TransactionID = transactionID;
	}

	public String getTransactionType() {
		return TransactionType;
	}

	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}

	public Date getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}

	public Account getSourceAccountNumber() {
		return SourceAccountNumber;
	}

	public void setSourceAccountNumber(Account sourceAccountNumber) {
		SourceAccountNumber = sourceAccountNumber;
	}

	
}
