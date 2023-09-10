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
	private long AccountNumber;
	
	@Column(name="accountType")
	private String AccountType;
	
	@Column(name="accountBalance")
	private BigDecimal AccountBalance;
	
	@Column(name="openDate")
	private Date OpenDate;
	
	public long getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public BigDecimal getAccountBalance() {
		return AccountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		AccountBalance = accountBalance;
	}

	public Date getOpenDate() {
		return OpenDate;
	}

	public void setOpenDate(Date openDate) {
		OpenDate = openDate;
	}

	public boolean isDebitCardReq() {
		return DebitCardReq;
	}

	public void setDebitCardReq(boolean debitCardReq) {
		DebitCardReq = debitCardReq;
	}

	public boolean isCreditCardReq() {
		return CreditCardReq;
	}

	public void setCreditCardReq(boolean creditCardReq) {
		CreditCardReq = creditCardReq;
	}

	public Customer getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(Customer customerID) {
		CustomerID = customerID;
	}

	@Column(name="debitCardReq")
	private boolean DebitCardReq;
	
	@Column(name="creditCardReq")
	private boolean CreditCardReq;
	
	@ManyToOne
	@JoinColumn(name="customerID", referencedColumnName="customerID")
	private Customer CustomerID;
	
	
	

}
