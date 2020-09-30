package com.lti.repo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Flight;


@Repository
public class FlightRepoImpl implements FlightRepo {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(Flight flight) {
		em.persist(flight);

	}

	
	public Flight fetch(int flightId) {
		Flight f= em.find(Flight.class, flightId);
		return f;
	}

	
	public List<Flight> fetchAll() {
		return em.createQuery("from Flight").getResultList();
	}

}
