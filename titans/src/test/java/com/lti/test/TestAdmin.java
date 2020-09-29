package com.lti.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Admin;
import com.lti.repo.AdminRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestAdmin {

	@Autowired
	private AdminRepo repo;

	@Test
	public void testSave() {
		Admin a = new Admin();
		a.setUserName("Saurabh");
		a.setPassword("Patna");
		repo.save(a);
	}

	@Test
	public void testFetch() {
		Admin a = repo.fetch(121);
		System.out.println(a.getUserName() + "\t" + a.getPassword() + "\t" + a.getAdminId());
	}

	@Test
	public void testFetchAll() {
		List<Admin> admin = repo.fetchAll();
		for (Admin a : admin)
			System.out.println(a.getUserName() + "\t" + a.getPassword());
	}
}
