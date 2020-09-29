package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Passenger;

@Repository
public class PassengerRepoImpl implements PassengerRepo {
    
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(Passenger pass) {
		em.persist(pass);

	}

	public Passenger fetch(int passengerId) {
		Passenger p= em.find(Passenger.class, passengerId);
		return p;
	}

	public List<Passenger> fetchAll() {
		return em.createQuery("from Passenger").getResultList();
	}

}