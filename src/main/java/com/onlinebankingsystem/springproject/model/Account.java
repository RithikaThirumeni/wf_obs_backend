package com.onlinebankingsystem.springproject.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(generator="custom-random-id")
	@GenericGenerator(name="custom-random-id", strategy = "com.onlinebankingsystem.springproject.util.Random8DigitIDGenerator")
	@Column(name="accountNumber")
	@Digits(integer=8, fraction=0)
	private long accountNumber;
	
	@NotBlank(message="Account type cannot be blank")
	@Pattern(regexp="Savings|Salary|Current")
	@Column(name="accountType")
	private String accountType;
	
	@DecimalMin(value="0.0", inclusive=true)
	@Digits(integer=10, fraction=2)
	@Column(name="accountBalance")
	private double accountBalance;
	
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
	
	@Column(nullable = false)
    private boolean activeStatus;

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@OneToMany(mappedBy="sourceAccountNumber", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Transaction> transactions;

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

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
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
