package com.lti.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.entity.Booking;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;
import com.lti.entity.User;

@Repository
public class UserRepoImpl implements UserRepo {

	@PersistenceContext
	private EntityManager em;

	
	public void Add(User user) {
		em.persist(user);
	}

	@Override
	public User Fetch(int custid) {
		return null;
	}

	public boolean isValidEmail(String email) {
		System.out.println(email);
		String sql = "select cs from Customer cs where cs.email= :email";
		TypedQuery<User> qry = em.createQuery(sql, User.class);
		qry.setParameter("email", email);
		List<User> customer = new ArrayList<>();
		try {
			customer = qry.getResultList();
		} catch (NoResultException e) {

		}
		System.out.println(customer);
		if (customer.isEmpty())
			return false;

		return true;
	}

	@Override
	public int registerUser(User customer) {
		int cust_id = 0;
		if (customer != null) {

			User c = em.merge(customer);
			cust_id = c.getCustomerId();

		}
		return cust_id;
	}

	@Override
	public int addTicketAndPassengerWithRegisteredCustomers(Booking ticket, List<Passenger> passenger, List<Seat> seats,
			Transaction transaction) {
		ticket.setTransaction(transaction);

		ticket.setPassenger(passenger);
		ticket.setSeats(seats);
		transaction.setBooking(ticket);
		for (Passenger p : passenger) {

			p.setBooking(ticket);

		}
		for (Seat s : seats) {

			s.setBooking(ticket);
		}
		Booking t = new Booking();
		try {
			t = em.merge(ticket);
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return t.getTicketId();
	}

}
