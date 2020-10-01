package com.lti.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Admin;
import com.lti.pojo.AdminLogin;
import com.lti.repo.AdminRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestAdmin {

	@Autowired
	private AdminRepo repo;

	@Test
	public void testSave() {
		Admin a = new Admin();
		a.setUserName("ranbir");
		a.setPassword("mumbai");
		repo.save(a);
	}

	@Test
	public void testFetch() {
		Admin a = repo.fetch(1111);
		System.out.println(a.getUserName() + "\t" + a.getPassword() + "\t" + a.getAdminId());
	}

	@Test
	public void testFetchAll() {
		List<Admin> admin = repo.fetchAll();
		for (Admin a : admin)
			System.out.println(" " + a.getUserName() + "\t" + a.getPassword());
	}

	@Test
	public void TestValidateAdmin() {

		AdminLogin login = new AdminLogin("Saurabh", "patna");

		boolean a = repo.validateAdmin(login);

		if (a) {
			System.out.println("Success for titans");
		} else
			System.out.println("Nahi hoga tumse!");
	}

	@Test
	void delAdmin() {
		// repo.delete()
	}
}