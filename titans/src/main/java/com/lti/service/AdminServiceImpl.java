package com.lti.service;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.pojo.AdminLogin;

import com.lti.repo.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo repo;

	@Transactional(value = TxType.REQUIRED)
	public void persist(Admin admin) {
		repo.save(admin);
	}

	@Override
	public  String adminLoginService(AdminLogin ald) {
	return	repo.login(ald);
	}

}