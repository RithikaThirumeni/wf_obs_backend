package com.onlinebankingsystem.springproject.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(generator="random8DigitIDGenerator")
	@GenericGenerator(name="random8DigitIDGenerator", strategy = "com.onlinebankingsystem.springproject.util.Random8DigitIDGenerator")
	@Column(name="transactionID")
	private long transactionID;
	
	@Column(name="transactionType")
	@Pattern(regexp="withdraw|deposit|transfer")
	private String transactionType;
	
	@DecimalMin(value="0.0", inclusive=true)
	@Digits(integer=10, fraction=2)
	@Column(name="transactionAmount")
	private double transactionAmount;
	
	@Column(name="transactionDate")
	private Date transactionDate;
	
	@Column(name="timestamp")
	private Timestamp timestamp;
	
	@ManyToOne
	@JoinColumn(name="sourceAccountNumber", referencedColumnName="accountNumber")
	private Account sourceAccountNumber;
	
	@ManyToOne
	@JoinColumn(name="receiverAccountNumber", referencedColumnName="accountNumber")
	private Account receiverAccountNumber;

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

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
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

	public Account getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(Account receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	

	
	
}
