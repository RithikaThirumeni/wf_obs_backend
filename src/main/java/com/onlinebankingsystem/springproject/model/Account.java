package com.onlinebankingsystem.springproject.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="accountNumber", length=10)
	private long accountNumber;
	
	@Column(name="accountType")
	private String accountType;
	
	@Column(name="accountBalance")
	private BigDecimal accountBalance;
	
	@Column(name="openDate")
	private Date openDate;

	@Column(name="debitCardReq")
	private boolean debitCardReq;
	
	@Column(name="creditCardReq")
	private boolean creditCardReq;
	
	@ManyToOne
	@JoinColumn(name="customerID", referencedColumnName="customerID")
	private Customer customerID;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public boolean isDebitCardReq() {
		return debitCardReq;
	}

	public void setDebitCardReq(boolean debitCardReq) {
		this.debitCardReq = debitCardReq;
	}

	public boolean isCreditCardReq() {
		return creditCardReq;
	}

	public void setCreditCardReq(boolean creditCardReq) {
		this.creditCardReq = creditCardReq;
	}

	public Customer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}
	
	
	

}
