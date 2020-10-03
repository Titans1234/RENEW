package com.lti.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.lti.entity.Booking;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;

public class BookingRepoImpl implements BookingRepo {

	@PersistenceContext
	private EntityManager em;

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t.getTicketId();

	}

	@Transactional
	public void saveBookingRepo(Booking booking) {
		em.persist(booking);
	}

}
