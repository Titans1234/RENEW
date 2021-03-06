package com.lti.repo;

import java.util.List;

import com.lti.entity.Booking;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;
import com.lti.entity.Transaction;
import com.lti.entity.User;

public interface UserRepo {

	public void Add(User user);

	public User Fetch(int custid);

	public boolean isValidEmail(String email);

	public int registerUser(User customer);

	public int addTicketAndPassengerWithRegisteredCustomers(Booking ticket, List<Passenger> passenger, List<Seat> seats,
			Transaction transaction);

}
