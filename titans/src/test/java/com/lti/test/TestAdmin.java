package com.lti.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.repo.AdminRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestAdmin {

	@Autowired
	private AdminRepo repo;

	@Test
	public void testSave() {
		Admin a = new Admin();
		a.setUserName("Manbir");
		a.setPassword("Patna");
		repo.save(a);
	}

	@Test
	public void testFetch() {
		Admin a = repo.fetch(1117);
		System.out.println(a.getUserName() + "\t" + a.getPassword() + "\t" + a.getAdminId());
	}

	@Test
	public void testFetchAll() {
		List<Admin> admin = repo.fetchAll();
		for (Admin a : admin)
			System.out.println(a.getUserName() + "\t" + a.getPassword());
	}
	
//	@Test
//	public void testAddFlight() {
//		Flight f= new Flight();
//		f.setFlightName("ME198");
//		f.setTotalSeat(70);
//		f.setFlightStatus("flying");
//		f.setFromCity("Mumbai");
//        f.setToCity("Patna");
//        f.setArrivalTime("8:45 AM");
//        f.setDepartureTime("10:45 AM");
//        f.setDuration("2 hrs");
//        f.setFare(12000.00);
//      
//	}
	
	@Test
	public void TestValidateAdmin() {
		String username1="Saurabh";
		String password1="Patna";
		boolean a =repo.validateAdmin(username1, password1);
		if(a) {
			System.out.println("Success for titans");
		}
		else 
			System.out.println("Nahi hoga tumse!");		
	}
}
