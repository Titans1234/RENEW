package com.lti.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Flight;
import com.lti.entity.Route;
import com.lti.repo.RouteRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestRoute {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private RouteRepo repo;

	@Test
	public void addRoute() {
		Route r = new Route();
		r.setArrivalTime("11:42:00");
		r.setDepartureTime("13:42:00");
		r.setDuration("15:42:00");
		r.setFare(5000);
		r.setFromCity("Delhi");
		r.setToCity("Banglore");

		Flight f = em.find(Flight.class, 131100);
		r.setFlight(f);

		repo.save(r);
	}
}
