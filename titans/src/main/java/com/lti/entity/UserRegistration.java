package com.lti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user_registration")
public class UserRegistration {

	@Id
	private long phoneNumber;
	
	@Column(length = 5)
	private String title;
	
	@Column(length = 25)
	private String name;
	
	@Column(length = 30)
	private String emailId;
	
	@Column(length = 30)
	private String password;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;//yyyy-mm-dd

	
	
	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
}
