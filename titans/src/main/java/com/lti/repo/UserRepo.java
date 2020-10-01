package com.lti.repo;

import java.util.List;

import com.lti.entity.User;

public interface UserRepo {
	public void Add(User cust);
	public List<User> fetchAll();
	public User Fetch(int custid);
	public void delete(int custid);
}
