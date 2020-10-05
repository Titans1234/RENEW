package com.lti.bridge;

public class LoginStatus {
  private boolean status;
  private String userName;
  private int userId;
//  
//public boolean isStatus() {
//	return status;
//}
  
//  
//public void setStatus(boolean status) {
//	this.status = status;
//}
  
  
public boolean getStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}




public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserName() {
	return userName;
}



public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}

}
