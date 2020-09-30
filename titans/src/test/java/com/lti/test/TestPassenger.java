package com.lti.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Passenger;
import com.lti.repo.PassengerRepo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestPassenger {

	@Autowired
	private PassengerRepo repo;
	
	@Test
	public void testSave() {
		Passenger p=new Passenger();
		p.setName("Mona");
		p.setAge(22);
		p.setGender("female");
	
		
		repo.save(p);
	}
	
	@Test
	public void testFetchAll() {
		List<Passenger> passengers = repo.fetchAll();
		for(Passenger p : passengers) {
			System.out.println(p.getName() +"\t" + p.getAge());
		}
	}
	
	@Test
	public void testFetch() {
		Passenger p = repo.fetch(10100);
		System.out.println(p.getName() +"\t" + p.getAge());
	}
	
}