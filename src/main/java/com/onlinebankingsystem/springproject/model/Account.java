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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="accountNumber", length=10)
	@Digits(integer=8, fraction=0)
	private long accountNumber;
	
	@NotBlank(message="Account type cannot be blank")
	@Pattern(regexp="savings|fixed-deposit")
	@Column(name="accountType")
	private String accountType;
	
	@DecimalMin(value="0.0", inclusive=true)
	@Digits(integer=10, fraction=2)
	@Column(name="accountBalance")
	private BigDecimal accountBalance;
	
	@FutureOrPresent(message="open date is not valid")
	@Column(name="openDate")
	private Date openDate;

	@Column(name="debitCardReq")
	private boolean debitCardReq;
	
	@Column(name="creditCardReq")
	private boolean creditCardReq;
	
	@ManyToOne
	@JsonBackReference
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
