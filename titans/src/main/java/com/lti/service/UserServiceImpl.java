package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.User;
import com.lti.pojo.UserLogin;
import com.lti.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
   
	@Autowired
	private UserRepo repo;

	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		return repo.fetchAll();
	}

	@Override
	public User fetch(int userId) {
		// TODO Auto-generated method stub
		return repo.Fetch(userId);
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		repo.Add(user);
		
	}

	@Override
	public void removeUser(int UserId) {
		// TODO Auto-generated method stub
		repo.delete(UserId);
		
	}

	@Override
	public boolean ValidateUser(UserLogin login) {
		// TODO Auto-generated method stub
		return false;
	}

}
