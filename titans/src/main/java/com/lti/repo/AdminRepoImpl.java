package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.User;


@Repository
public class AdminRepoImpl implements AdminRepo {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Admin admin) {
		em.persist(admin);

	}

	public Admin fetch(int adminId) {
		Admin f = em.find(Admin.class, adminId);
		return f;
	}

	public List<Admin> fetchAll() {
		return em.createQuery("from admin").getResultList();
	}

	@Override
	public void AdminAddFlights(Flight flight) {
		/* flight1.setArrivalTime(flight.getArrivalTime()); */

	}

	@Override
	public void AdminRemoveFlights(int flightId) {

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
	public boolean validateAdmin(String username1, String password1) {
		/*
		 * String sql =
		 * "select username,password from Admin  where ad.username=: username and ad.password =:password"
		 * ; TypedQuery<Admin> qry = em.createQuery(sql, Admin.class);
		 * qry.setParameter("username", username); qry.setParameter("password",
		 * password); List<Admin> admin = qry.getResultSet(); if (admin.isEmpty())
		 * return false;
		 * 
		 * return true;
		 */

		/*
		 * String admin=
		 * "select username, password from Admin where username=username1 and password=password1"
		 * ;
		 * 
		 * Query q = em.createNamedQuery("admin"); q.setParameter("username",
		 * admin.getUsername()); q.setParameter("password", admin.getPassword()); return
		 * (Admin) query.getSingleResult();
		 */

		
	}

}
