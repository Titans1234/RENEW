package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.User;
import com.lti.pojo.AdminLogin;

@Repository
public class AdminRepoImpl implements AdminRepo {

	@PersistenceContext
	private EntityManager em;

	
	public void save(Admin admin) {
		em.persist(admin);

	}

	@Override
	public void AdminAddFlights(Flight flight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AdminRemoveFlights(int flightId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Flight> AdminViewFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight AdminSearchFlight(int flightid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(AdminLogin ald) {
		String query = "select a.userName from Admin a where a.userName= :u1 and a.password= :p1";
		List<String> li = em.createQuery(query).setParameter("u1", ald.getUsername()).setParameter("p1", ald.getPassword()).getResultList();
		if (li.size() > 0) {
			System.out.println(li.get(0));
			String a= li.get(0);
			return a;
		}
		return null;
		/*
			 * else { System.out.println("No data found"); } return null;
			 */
			
	}

	@Override
	public void cancelFlight(int flightId) {
		String query1="update Flights f set f.flightStatus='cancelled' where f.flightId= :f1";
		String query2="update Booking b set b.refundAmount=b.bookingAmount where b.flightId= :f2";
		String query3="update Passenger p set p.status='cancelled' where c.bookingId in "
				+ "(select b.bookingId from Booking b where b.flightId= :f3)";
		
		int flightsUpdatedRow=em.createQuery(query1).setParameter("f1", flightId).executeUpdate();
	}

}