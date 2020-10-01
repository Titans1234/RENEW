package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.pojo.AdminLogin;

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
		return em.createQuery("from Admin").getResultList();
	}

	@Override
	public boolean validateAdmin(AdminLogin login) {
		System.out.println(login.getUsername() + " :" + login.getPassword());
		String username = login.getUsername();
		String password = login.getPassword();
		String sql = "select ad from Admin ad where ad.userName=:username and ad.password=:password";
		// Query query = em.createQuery(sql);
		Query q = em.createQuery(sql, Admin.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		Admin a = (Admin) q.getSingleResult();
		// System.out.println(((Admin) a).getUserName());
		System.out.println(a.getUserName());
		// names.stream().forEach((x) -> System.out.println(x));
		return false;

		// String sql = "select ad from Admin ad where ad.username = : username and
		// ad.password = :password";
		/*
		 * TypedQuery<Admin> qry = em.createQuery(sql, Admin.class);
		 * qry.setParameter("username",username); qry.setParameter("password",password);
		 * List<Admin> admin = qry.getResultList();
		 */

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

}