package com.lti.service;

import java.util.List;

import com.lti.entity.User;
import com.lti.pojo.UserLogin;

public interface UserService {
	// -----------UserDashboard
	public List<User> fetchAll();

	public User fetch(int userId);

	public void register(User user);

	public void removeUser(int UserId);

	public boolean ValidateUser(UserLogin login);

	// ---------Booking----------//

}
