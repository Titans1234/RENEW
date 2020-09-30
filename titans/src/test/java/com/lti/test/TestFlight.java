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
		f.setFlightStatus("flying");
		f.setFromCity("Mumbai");
        f.setToCity("Bangalore");
        f.setArrivalTime("11:45 AM");
        f.setDepartureTime("7:45 AM");
        f.setDuration("4 hrs");
        f.setFare(4000.00);
		repo.save(f);
	}
	
	//fetchAll is not working as of now.
	
	@Test
	public void testFetchAll() {
		List<Flight> flights = repo.fetchAll();
		for(Flight f : flights) {
			System.out.println(f.getFlightName() +"\t" + f.getTotalSeat()
			+"\t" +f.getFlightStatus()+"\t"+ f.getFromCity() +"\t" +
					f.getToCity() +"\t" +f.getArrivalTime() +"\t" 
					+f.getDepartureTime() +"\t" +f.getDuration()
					);
		}
	}
	
	@Test
	public void testFetch() {
		Flight f = repo.fetch(131103);
		System.out.println(f.getFlightName() +"\t" + f.getTotalSeat()
		+"\t" +f.getFlightStatus()+"\t"+ f.getFromCity() +"\t" +
				f.getToCity() +"\t" +f.getArrivalTime() +"\t" 
				+f.getDepartureTime() +"\t" +f.getDuration()+
				"\t" + "Rs." + f.getFare());
	}
	
}
