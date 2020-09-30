package com.lti.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Flight;
import com.lti.entity.Passenger;
import com.lti.repo.FlightRepo;
import com.lti.repo.PassengerRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestFlight {

	@Autowired
	private FlightRepo repo;
	
	@Test
	public void testSave() {
		Flight f = new Flight();
		f.setFlightName("DE198");
		f.setTotalSeat(70);
		
		repo.save(f);
	}
	
	@Test
	public void testFetchAll() {
		List<Flight> flights = repo.fetchAll();
		for(Flight f : flights) {
			System.out.println(f.getFlightName() +"\t" + f.getTotalSeat());
		}
	}
	
	@Test
	public void testFetch() {
		Flight f = repo.fetch(131100);
		System.out.println(f.getFlightName() +"\t" + f.getTotalSeat());
	}
	
}
