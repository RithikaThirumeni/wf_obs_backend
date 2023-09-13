package com.onlinebankingsystem.springproject.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

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
	@Digits(integer=8, fraction=0)
	private Integer customerID;
	
	@NotBlank(message="Name connot be blank")
	@Column(name="firstName", nullable=false)
	private String firstName;
	
	@NotBlank(message="Name connot be blank")
	@Column(name="lastName", nullable=false)
	private String lastName;
	
	@Digits(integer=10, fraction=0, message="Phone number must be 10 digits long")
	@Min(value=1000000000, message="Phone number should be 10 digits")
	@Column(name="phoneNumber", nullable=false)
	private long phoneNumber;
	
	@Past(message="invalid date of birth")
	@Column(name="dateOfBirth", nullable=false)
	private Date dateOfBirth;	
	
	@Email(message="email ID should be valid")
	@Column(name="emailID", nullable=false, unique=true)
	private String emailID;
	
	@Length(min=6, max=15, message="password should be between 6 to 15 characters")
	@Column(name="password", nullable=false, length=10)
	private String password;
	
	@Digits(integer=4, fraction=0, message="pin should be 4 digits")
	@Column(name="pin", nullable=false, length=4)
	private int pin;
	
	@Column(name="address")
	private String residentAddress;
	
	@OneToMany(mappedBy="customerID", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Account> accounts;
	
	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getResidentAddress() {
		return residentAddress;
	}

	public void setResidentAddress(String residentAddress) {
		this.residentAddress = residentAddress;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public void addAccounts(List<Account> accounts) {
		this.accounts.addAll(accounts);
	}
	

}
