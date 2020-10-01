package com.lti.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.User;
import com.lti.repo.UserRepo;
import com.lti.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUser {
	@Autowired
	private UserService repo;

	@Test
	public void testAdd() {
		// ApplicationContext context=new
		// ClassPathXmlApplicationContext("stereotypeConfig.xml");
		User user = new User();
		user.setName("Saurabh");
		user.setAge(22);
		user.setEmail("sahisaurabh511@gmail.com");
		user.setPassword("Titans");
		user.setContact("8873056889");
		user.setGender("Male");
		repo.register(user);
	}

	@Test
	public void testFetch() {

		User f = repo.fetch(10103);
		System.out.println(f.toString());

	}

	@Test
	public void testList() {

		List<User> p = new ArrayList<User>();
		p = repo.fetchAll();
		for (User c : p) {
			System.out.println(c.toString());
		}

	}

	@Test
	public void deleteCustomer() {
		repo.removeUser(10124);
	}

}
