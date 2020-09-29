package com.lti.repo;

import java.util.List;

import com.lti.entity.Admin;

public interface AdminRepo {

	void save(Admin Admin);

	Admin fetch(int adminId);
	
    List<Admin> fetchAll();
    
}