package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_registration")
public class AdminRegistration {

	@Id
	private String username;
	
	@Column(length = 25)
	private String adminName;
	
	@Column(length = 25)
	private String password;

	public String getUsername() {
		return username;
	}
	

	public AdminRegistration(String username, String adminName, String password) {
		super();
		this.username = username;
		this.adminName = adminName;
		this.password = password;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
