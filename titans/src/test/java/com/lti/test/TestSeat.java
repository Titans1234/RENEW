package com.lti.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Flight;
import com.lti.entity.Seat;
import com.lti.repo.SeatRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSeat {

	@Autowired
	private SeatRepo repo;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testSave() {
		Seat s = new Seat();

		Flight f = em.find(Flight.class, 131100);

		s.setSeats(2);
		s.setGender("economy");
		s.setDate(LocalDate.of(2026, 1, 23));
		s.setFlight(f);
		repo.save(s);
	}
}