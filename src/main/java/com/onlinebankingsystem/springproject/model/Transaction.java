package com.onlinebankingsystem.springproject.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

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
	private long transactionID;
	
	@Column(name="transactionType")
	private String transactionType;
	
	@Column(name="transactionAmount")
	private BigDecimal transactionAmount;
	
	@Column(name="transactionDate")
	private Date transactionDate;
	
	@Column(name="timestamp")
	private Timestamp timestamp;
	
	@ManyToOne
	@JoinColumn(name="sourceAccountNumber", referencedColumnName="accountNumber")
	private Account sourceAccountNumber;

	@Column(name="receiverDescription")
	private String receiverDescription;

	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Account getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(Account sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getReceiverDescription() {
		return receiverDescription;
	}

	public void setReceiverDescription(String receiverDescription) {
		this.receiverDescription = receiverDescription;
	}

	
	
}
