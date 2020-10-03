package com.lti.repo;

import java.util.List;

import com.lti.entity.Booking;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;

public interface BookingRepo {
	public int addTicketAndPassengerWithRegisteredCustomers(Booking ticket, List<Passenger> passenger, List<Seat> seats,
			Transaction transaction);

	void saveBookingRepo(Booking booking) ;
}
