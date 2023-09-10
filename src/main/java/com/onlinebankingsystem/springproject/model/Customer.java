package com.onlinebankingsystem.springproject.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customerID")
	private Integer CustomerID;
	
	@Column(name="firstName", nullable=false)
	private String FirstName;
	
	@Column(name="lastName", nullable=false)
	private String LastName;
	
	@Column(name="phoneNumber", nullable=false)
	private long PhoneNumber;
	
	@Column(name="dateOfBirth", nullable=false)
	private Date DateOfBirth;	
	
	@Column(name="emailID", nullable=false, unique=true)
	private String EmailID;
	
	@Column(name="password", nullable=false, length=10)
	private String Password;
	
	@Column(name="pin", nullable=false, length=4)
	private int pin;
	
	@Column(name="address")
	private String ResidentAddress;
	
	@OneToMany(mappedBy="CustomerID", cascade=CascadeType.ALL)
	private List<Account> accounts;
	
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public String getPassword() {
		return Password;
	}
	public String getResidentAddress() {
		return ResidentAddress;
	}
	public void setResidentAddress(String residentAddress) {
		ResidentAddress = residentAddress;
	}
	public Date getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public Integer getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(Integer customerID) {
		CustomerID = customerID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public long getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getEmailID() {
		return EmailID;
	}
	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

}
