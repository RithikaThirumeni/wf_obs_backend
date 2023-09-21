package com.onlinebankingsystem.springproject.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminID;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String emailID;

    public Long getAdminID() {
		return adminID;
	}

	public void setAdminID(Long adminID) {
		this.adminID = adminID;
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

	@NotBlank
    @Column(nullable = false)
    private String password;
}
