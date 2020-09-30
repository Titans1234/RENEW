package com.lti.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Seat;

@Repository
public class SeatRepoImpl implements SeatRepo {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Seat seat) {
		em.persist(seat);
	}

	@Override
	public Seat Fetch(int seatid) {

		return null;
	}

	@Override
	public void delete(int seatid) {

	}

}