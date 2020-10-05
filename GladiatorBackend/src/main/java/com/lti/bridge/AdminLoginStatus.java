package com.lti.bridge;

public class AdminLoginStatus {
	 private boolean status;
	  private String userName;
	  private int password;
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AdminLoginStatus [status=" + status + ", userName=" + userName + ", password=" + password + "]";
	}
	  
}
