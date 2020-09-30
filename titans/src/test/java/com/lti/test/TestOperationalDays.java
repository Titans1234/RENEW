package com.lti.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.entity.Flight;
import com.lti.entity.OperationalDays;
import com.lti.repo.OperationalDaysRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestOperationalDays {

	@Autowired
	private OperationalDaysRepo repo;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testAddOperationalDays() {
		OperationalDays o = new OperationalDays();
		o.setOperationalDays("Tuesday");
		Flight f = em.find(Flight.class, 131103);
		o.setFlight(f);
		repo.save(o);
	}

	@Test
	public void testFetch() {
		OperationalDays o = repo.fetch(1010101);
		System.out.println(o.getOperationalDays() + "\t" + o.getOperationalId() + "\t");
	}

	@Test
	public void testFetchAll() {
		List<OperationalDays> o = repo.fetchAll();
		for (OperationalDays s : o) {
			System.out.println(s.getOperationalDays() + "\t" + s.getOperationalId() + "\t");
		}

	}
}
